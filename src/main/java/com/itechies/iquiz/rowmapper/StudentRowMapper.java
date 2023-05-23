package com.itechies.iquiz.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.itechies.iquiz.model.Student;

public class StudentRowMapper implements RowMapper<Student> {

	@Override
	public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
		Student student = new Student();
		student.setId(rs.getInt("id"));
		student.setDepartment(rs.getInt("department"));
		student.setName(rs.getString("name"));
		student.setPassword(rs.getString("password"));
		student.setStudent_id(rs.getString("student_id"));
		return student;
	}

}
