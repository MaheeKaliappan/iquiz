package com.itechies.iquiz.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.itechies.iquiz.model.Question;

public class QuestionRowMapper implements RowMapper<Question> {

	@Override
	public Question mapRow(ResultSet rs, int rowNum) throws SQLException {
		Question question = new Question();
		question.setAdded_by(rs.getInt("added_by"));
		question.setAdded_on(rs.getString("added_on"));
		question.setExam_id(rs.getInt("exam_id"));
		question.setId(rs.getInt("id"));
		question.setModified_by(rs.getInt("modified_by"));
		question.setModified_on(rs.getString("modified_on"));
		question.setQuestion(rs.getString("question"));
		
		return question;
	}

}
