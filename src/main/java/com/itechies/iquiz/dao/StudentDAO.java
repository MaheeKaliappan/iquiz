package com.itechies.iquiz.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.itechies.iquiz.model.Student;
import com.itechies.iquiz.rowmapper.StudentRowMapper;

@Service
public class StudentDAO {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	//ADD STUDENT
	public String addStudent(Student student) {
		String sql = "INSERT INTO STUDENT(department,name,password,student_id)VALUES (?,?,?,?)";
		jdbcTemplate.update(sql, student.getDepartment(), student.getName(), student.getPassword(),
				student.getStudent_id());
		return student.getName();

	}
	
	//VIEW STUDENTS
	public List<Student> fetchAllStudents() {
		String sql = "SELECT * FROM Student";
		return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Student.class));
	}

	//DELETE STUDENT
	public void deleteStudent(Integer id) {
		String sql = "DELETE FROM STUDENT WHERE ID=?";
		jdbcTemplate.update(sql, id);

	}

	//FETCH ONE STUDENT RECORD
	public Student fetchStudentDetail(Integer id) {
		String sql = "SELECT * FROM Student WHERE ID  = ?";
		Student student = jdbcTemplate.queryForObject(sql, new Object[] { id }, new StudentRowMapper());

		return student;
	}

	//UPDATE STUDENT
	public void updateStudent(Integer id, Student student) {
		String sql=" UPDATE STUDENT SET name = ?, department = ?,student_id=?,password=? WHERE id = ?";
		jdbcTemplate.update(sql, student.getName(),student.getDepartment(), student.getStudent_id(),
				student.getPassword(),student.getId());
		
	}

}
