package com.itechies.iquiz.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.itechies.iquiz.dao.DepartmentDAO;
import com.itechies.iquiz.model.Department;


@RestController
public class DepartmentController {

	@Autowired
	DepartmentDAO departmentDAO;

	// ADD DEPARTMENT
	@RequestMapping(value = "/add_department", method = RequestMethod.POST)
	public String addDepartment(@ModelAttribute Department department) {
		String name = departmentDAO.addDepartment(department);
		return name + " Added SuccessFully";
	}

	// UPDATE DEPARTMENT
	@RequestMapping(value = "/update_department/{id}", method = RequestMethod.POST)
	public String updateDepartment(@PathVariable Integer id, @ModelAttribute Department department) {
		// www-for-urlencoded la value pass pannanum
		departmentDAO.updateDepartment(id, department);
		return "Updated Successfully";
	}

	// VIEW DEPARTMENT
	@ResponseBody
	@RequestMapping(value = "/view_departments", method = RequestMethod.GET)
	public Map<String, Object> viewDepartments() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("students", departmentDAO.fetchAllDepartments());
		return map;

	}

	// DELETE DEPARTMENT
	@RequestMapping(value = "/delete_department/{id}", method = RequestMethod.DELETE)
	public String deleteDepartment(@PathVariable Integer id) {
		System.out.println(id);
		departmentDAO.deleteDepartment(id);
		return "Deleted SuccessFully";

	}

	// FETCH ONE DEPARTMENT RECORD
	@ResponseBody
	@RequestMapping(value = "/fetch_department/{id}", method = RequestMethod.GET)
	public Map<String, Department> fetchDepartment(@PathVariable Integer id) {

		
		Department department = departmentDAO.fetchDepartmentDetail(id);
		Map<String, Department> map = new HashMap<String, Department>();
		map.put("student", department);
		return map;
	}

}
