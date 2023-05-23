package com.itechies.iquiz.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.itechies.iquiz.model.Staff;


public class StaffRowMapper implements RowMapper<Staff> {

	@Override
	public Staff mapRow(ResultSet rs, int rowNum) throws SQLException {
		Staff staff = new Staff();
		staff.setId(rs.getInt("id"));
		staff.setDepartment(rs.getInt("department"));
		staff.setName(rs.getString("name"));
		staff.setPassword(rs.getString("password"));
		staff.setStaf_id(rs.getString("staf_id"));
		return staff;
	}

}