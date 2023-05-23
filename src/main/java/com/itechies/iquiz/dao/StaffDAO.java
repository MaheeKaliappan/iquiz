	package com.itechies.iquiz.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.itechies.iquiz.model.Staff;
import com.itechies.iquiz.rowmapper.StaffRowMapper;


@Service
public class StaffDAO {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	//ADD STAFF
	public String addStaff(Staff staff) {          
		String sql = "INSERT INTO STAFF(name,department,staf_id,password)VALUES (?,?,?,?)";
		System.out.println(staff.getName());
		System.out.println(staff.getPassword());
		System.out.println(staff.getStaf_id());
		System.out.println(staff.getDepartment());
		
		jdbcTemplate.update(sql, staff.getName(), staff.getDepartment(),staff.getStaf_id(), staff.getPassword());
		return staff.getName();

	}
	
	//VIEW STAFF
	public List<Staff> fetchAllStaff() {
		String sql = "SELECT * FROM Staff";
		return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Staff.class));
	}

	//DELETE STAFF
	public void deleteStaff(Integer id) {
		String sql = "DELETE FROM STAFF WHERE ID=?";
		jdbcTemplate.update(sql, id);

	}

	//FETCH ONE STAFF RECORD
	public Staff fetchStaffDetail(Integer id) {
		String sql = "SELECT * FROM Staff WHERE ID  = ?";
		Staff staff = jdbcTemplate.queryForObject(sql, new Object[] { id }, new StaffRowMapper());
		return staff;
	}

	//UPDATE STAFF
	public void updateStaff(Integer id, Staff staff) {
		String sql=" UPDATE STAFF SET name = ?, department = ?,staf_id=?,password=? WHERE id = ?";
		jdbcTemplate.update(sql, staff.getName(),staff.getDepartment(), staff.getStaf_id(),
				staff.getPassword(),staff.getId());
		
	}

}

