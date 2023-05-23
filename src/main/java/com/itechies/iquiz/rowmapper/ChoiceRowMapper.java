package com.itechies.iquiz.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.itechies.iquiz.model.Choice;

public class ChoiceRowMapper implements RowMapper<Choice> {

	@Override
	public Choice mapRow(ResultSet rs, int rowNum) throws SQLException {
		Choice choice = new Choice();
		choice.setChoice(rs.getString("choice"));
		choice.setId(rs.getInt("id"));
		choice.setExam_id(rs.getInt("exam_id"));
		choice.setStatus(rs.getBoolean("status"));
		choice.setQuestion_id(rs.getInt("question_id"));
		return choice;
	}
	

}
