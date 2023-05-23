package com.itechies.iquiz.dao;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import com.itechies.iquiz.model.Exam;
import com.itechies.iquiz.rowmapper.ExamRowMapper;


@Service
public class ExamDAO {
	
	@Autowired
	JdbcTemplate  jdbcTemplate;
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
	  
	  
	

	//ADD EXAM
	public String addExam(Exam exam) {
		String sql="INSERT INTO exam_details (name,subject_code,date_of_exam,exam_time,department,added_by,added_on,modified_by,modified_on)VALUES(?,?,?,?,?,?,?,?,?)";

		exam.setAddedOn(dtf.format(LocalDateTime.now()));
		exam.setModifiedOn(dtf.format(LocalDateTime.now()));
		exam.setDateOfExam(dtf.format(LocalDateTime.now()));
		
		exam.setAddedBy(1);
		exam.setModifiedBy(1);
		jdbcTemplate.update(sql,exam.getName(),exam.getSubjectCode(),exam.getDateOfExam(),exam.getExamTime(),exam.getDepartment(),exam.getAddedBy(),exam.getAddedOn(),exam.getModifiedBy(),exam.getModifiedOn());
		return exam.getName();
	}
		
		//VIEW EXAM
		public List<Exam> fetchAllExams() {
			String sql = "SELECT * FROM exam_details";
			return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Exam.class));
		}

		//DELETE EXAM
		public void deleteExam(Integer id) {
			String sql = "DELETE FROM exam_details WHERE ID=?";
			jdbcTemplate.update(sql, id);

		}

		//FETCH ONE EXAM RECORD
		public Exam fetchExamDetail(Integer id) {
			String sql = "SELECT * FROM exam_details WHERE ID  = ?";
			Exam exam = jdbcTemplate.queryForObject(sql, new Object[] { id }, new ExamRowMapper());
			return exam;
		}

		//UPDATE EXAM
		public void updateExam(Integer id, Exam exam) {
			exam.setModifiedOn(dtf.format(LocalDateTime.now()));
			
			exam.setDateOfExam(dtf.format(LocalDateTime.now()));
			exam.setModifiedBy(1);
			String sql=" UPDATE exam_details SET name = ?, subject_code = ?,exam_time=?,department=?,status=? ,modified_on=?,modified_by=?,date_of_exam=? WHERE id = ?";
			jdbcTemplate.update(sql, exam.getName(),exam.getSubjectCode(),exam.getExamTime(),exam.getDepartment(),exam.getExamStatus(),exam.getModifiedOn(),exam.getModifiedBy(),exam.getDateOfExam(),id);
			
		}

		
	

}
