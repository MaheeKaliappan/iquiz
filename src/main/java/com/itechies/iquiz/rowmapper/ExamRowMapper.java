package com.itechies.iquiz.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.itechies.iquiz.model.Exam;

public class ExamRowMapper implements RowMapper<Exam>{

	@Override
	public Exam mapRow(ResultSet rs, int rowNum) throws SQLException {
		Exam exam = new Exam();
		exam.setId(rs.getInt("id"));
		exam.setAddedBy(rs.getInt("added_by"));
		exam.setAddedOn(rs.getString("added_on"));
		exam.setDateOfExam(rs.getString("date_of_exam"));
		exam.setDepartment(rs.getInt("department"));
		exam.setName(rs.getString("name"));
		exam.setExamStatus(rs.getInt("status"));
		exam.setExamTime(rs.getString("exam_time"));
		exam.setModifiedBy(rs.getInt("modified_by"));
		exam.setModifiedOn(rs.getString("modified_on"));
		exam.setSubjectCode(rs.getString("subject_code"));
		
		return exam;
		
	}

}
