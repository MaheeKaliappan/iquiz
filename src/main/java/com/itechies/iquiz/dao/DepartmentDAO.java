package com.itechies.iquiz.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.itechies.iquiz.model.Department;
import com.itechies.iquiz.rowmapper.DepartmentRowMapper;

@Service
public class DepartmentDAO {

	@Autowired
	JdbcTemplate jdbcTemplate;

	// ADD DEPARTMENT
	public String addDepartment(Department department) {
		String sql = "INSERT INTO DEPARTMENT(name)VALUES (?)";
		jdbcTemplate.update(sql, department.getName());
		return department.getName();

	}

	// VIEW DEPARTMENT
	public List<Department> fetchAllDepartments() {
		String sql = "SELECT * FROM department";
		return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Department.class));
	}

	// DELETE DEPARTMENT
	public void deleteDepartment(Integer id) {
		String sql = "DELETE FROM DEPARTMENT WHERE ID=?";
		jdbcTemplate.update(sql, id);

	}

	// FETCH ONE DEPARTMENT RECORD
	public Department fetchDepartmentDetail(Integer id) {
		String sql = "SELECT * FROM Department WHERE ID  = ?";
		Department department = jdbcTemplate.queryForObject(sql, new Object[] { id }, new DepartmentRowMapper());

		return department;
	}

	// UPDATE DEPARTMENT
	public void updateDepartment(Integer id, Department department) {
		String sql = "UPDATE DEPARTMENT SET name=? WHERE id=?";
		jdbcTemplate.update(sql, department.getName(), id);

	}

}
