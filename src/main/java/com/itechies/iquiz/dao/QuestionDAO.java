package com.itechies.iquiz.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import com.itechies.iquiz.model.Question;
import com.itechies.iquiz.model.QuestionAndAnswer;
import com.itechies.iquiz.rowmapper.ExamRowMapper;
import com.itechies.iquiz.rowmapper.FetchMarkRowMapper;
import com.itechies.iquiz.rowmapper.FetchQuestionPaperRowMapper;
import com.itechies.iquiz.rowmapper.QuestionAndAnswerRowMapper;
import com.itechies.iquiz.rowmapper.QuestionRowMapper;
import com.mysql.jdbc.Statement;

@Service
public class QuestionDAO {

	@Autowired
	JdbcTemplate jdbcTemplate;
	@Autowired
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");

	public void updateQuestion(Integer id, Question question) {
		question.setModified_on(dtf.format(LocalDateTime.now()));
		question.setModified_by(1);
		String sql = " UPDATE questions SET question = ?, exam_id = ?, modified_by=? ,modified_on=? WHERE id = ?";
		jdbcTemplate.update(sql, question.getQuestion(), question.getExam_id(), question.getModified_by(),
				question.getModified_on(), id);

	}

	public Question fetchQuestionDetail(Integer id) {
		String sql = "SELECT * FROM questions WHERE ID  = ?";
		Question question = jdbcTemplate.queryForObject(sql, new Object[] { id }, new QuestionRowMapper());

		return question;
	}

	public void deleteQuestion(Integer id) {
		String sql = "DELETE FROM questions WHERE ID=?";
		jdbcTemplate.update(sql, id);

	}

	public List<Question> fetchAllQuestions() {
		String sql = "SELECT * FROM questions";
		return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Question.class));
	}

	public void addQuestion(Question question) {
		question.setAdded_on(dtf.format(LocalDateTime.now()));
		question.setModified_on(dtf.format(LocalDateTime.now()));
		question.setAdded_by(1);
		question.setModified_by(1);
		String sql = "INSERT INTO questions (question,exam_id,added_by,added_on,modified_by,modified_on) VALUES (?,?,?,?,?,?)";
		jdbcTemplate.update(sql, question.getQuestion(), question.getExam_id(), question.getAdded_by(),
				question.getAdded_on(), question.getModified_by(), question.getModified_on());

	}
	/*
	 * public List<Student> listStudents() { String SQL = "select * from Student";
	 * List <Student> students = jdbcTemplateObject.query(SQL, new StudentMapper());
	 * return students; }
	 */

	public List<QuestionAndAnswer> fetchQuestionAndAnswer(Integer id) {

		String sql = "SELECT QUESTIONS.ID,QUESTIONS.QUESTION,choice.id,choice.choice,choice.status FROM QUESTIONS inner join exam_details on EXAM_ID= ?  inner join choice on questions.id=choice.question_id";
		List<QuestionAndAnswer> questionAndAnswer = (List<QuestionAndAnswer>) jdbcTemplate.query(sql,
				new Object[] { id }, new QuestionAndAnswerRowMapper());

		return questionAndAnswer;
	}

	public void updateStudentTable(List<QuestionAndAnswer> shuffleQuestionAnswer, Integer exam_id, Integer student_id) {
		// String sqlQuestion="INSERT INTO student_question_copy
		// (s_no,exam_id,question_id,student_id,correct_ans_id) VALUES
		// (:s_no,:exam_id,:question_id,:student_id,:correct_ans_id)";
		String sqlQuestion = "INSERT INTO student_question_copy (s_no,exam_id,question_id,student_id) VALUES (?,?,?,?)";
		String sqlChoice = "INSERT INTO student_answer_copy (s_no,question_copy_id,choice_id,exam_id) VALUES (?,?,?,?)";

		Connection con;
		PreparedStatement stmt;
		int innerLoop = shuffleQuestionAnswer.get(0).getChoices_ids().size();
		int primaryKey = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/onlinequiz?autoReconnect=true&useSSL=false",
					"root", "root");
			stmt = con.prepareStatement(sqlQuestion, Statement.RETURN_GENERATED_KEYS);
			// ("INSERT INTO student_question_copy VALUES (?,?,?,?,?)");
			for (int i = 0; i < shuffleQuestionAnswer.size(); i++) {
				stmt.setInt(1, i + 1);
				stmt.setInt(2, exam_id);
				stmt.setInt(3, shuffleQuestionAnswer.get(i).getQuestion_id());
				stmt.setInt(4, student_id);

				stmt.addBatch();
				stmt.executeBatch();
				ResultSet resultSet = stmt.getGeneratedKeys();
				while (resultSet.next())
					primaryKey = resultSet.getInt(1);
				for (int j = 0; j < innerLoop; j++)
					jdbcTemplate.update(sqlChoice, j + 1, primaryKey,
							shuffleQuestionAnswer.get(i).getChoices_ids().get(j), exam_id);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		for (int i = 0; i < shuffleQuestionAnswer.size(); i++) {
//			jdbcTemplate.update(sqlQuestion, i + 1, exam_id, shuffleQuestionAnswer.get(i).getQuestion_id(), student_id,
//					shuffleQuestionAnswer.get(i).getCorrect_ans_id());
//
//			for (int j = 0; j < innerLoop; j++)
//				jdbcTemplate.update(sqlChoice, primaryKey, shuffleQuestionAnswer.get(i).getQuestion_id(),
//						shuffleQuestionAnswer.get(i).getChoices_ids().get(j), exam_id);
//		}

	}

	public List<QuestionAndAnswer> showStudentQuestion(Integer exam_id, Integer student_id) {
		// String sql = "select questions.question,questions.id as question_id,choice.id
		// as choice_id,choice.choice,choice.status from questions inner join
		// student_question_copy on questions.id= student_question_copy.question_id
		// inner join choice on choice.id=student_question_copy.ans_id where student_id=
		// + student_id + " and student_question_copy.exam_id=?";
		String sql="select questions.question, choice.choice ,questions.id ,choice.id   from questions inner join student_question_copy  on student_question_copy.question_id = questions.id  inner join student_answer_copy on student_answer_copy.question_copy_id=student_question_copy.id inner join choice on student_answer_copy.choice_id = choice.id where student_question_copy.student_id= "+student_id+" and student_question_copy.exam_id=?";
		 //jdbcTemplate.query(sql,new Object[]{ student_id ,exam_id}, new QuestionAndAnswerRowMapper());
		List<QuestionAndAnswer> questionAndAnswer = (List<QuestionAndAnswer>) jdbcTemplate.query(sql,new Object[] { exam_id }, new FetchQuestionPaperRowMapper());
		return questionAndAnswer;
	}

	public void sumbitAnswer(Integer exam_id, Integer student_id, QuestionAndAnswer questionAnswer) {
		String sql="UPDATE student_question_copy SET student_ans_id = ? WHERE exam_id=? AND question_id=? AND student_id=?";
		
		jdbcTemplate.update(sql, questionAnswer.getAns_id(), exam_id, questionAnswer.getQuestion_id(),student_id);
		
	}

	public List<Integer> viewMark(Integer exam_id, Integer student_id) {
		String sql="select sum(choice.status) as mark from choice inner join student_question_copy on  student_question_copy.question_id=choice.question_id and student_question_copy.student_ans_id=choice.id  where student_question_copy.exam_id= ? and student_question_copy.student_id= "+student_id;
		return jdbcTemplate.query(sql,new Object[] { exam_id}, new FetchMarkRowMapper());
	}

}
