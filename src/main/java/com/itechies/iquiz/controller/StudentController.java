package com.itechies.iquiz.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.itechies.iquiz.dao.StudentDAO;

import com.itechies.iquiz.model.Student;

@RestController
public class StudentController {

	@Autowired
	StudentDAO studentDao;

	//ADD STUDENT
	@RequestMapping(value = "/add_student", method = RequestMethod.POST)
	public String addStudent(@ModelAttribute Student student) {
		String name = studentDao.addStudent(student);
		return name +" Added SuccessFully";
	}

	//VIEW STUDENTS
	@ResponseBody
	@RequestMapping(value = "/view_students", method = RequestMethod.GET)
	public Map<String, Object> viewStudent() {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("students", studentDao.fetchAllStudents());
		return map;

	}

	//DELETE STUDENT
	@RequestMapping(value = "/delete_student/{id}", method = RequestMethod.DELETE)
	public String deleteStudent(@PathVariable Integer id) {
		System.out.println(id);
		studentDao.deleteStudent(id);
		return "Deleted SuccessFully";

	}

	//FETCH ONE STUDENT RECORD
	// doubt yen student ah value ah anupa mudila??
	//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) ithu add pannanum model class la
	@ResponseBody
	@RequestMapping(value = "/fetch_student/{id}", method = RequestMethod.GET)
	public Map<String, Student> fetchStudent(@PathVariable Integer id) {

		
		Student student=studentDao.fetchStudentDetail(id);
		Map<String, Student> map = new HashMap<String, Student>();
		map.put("student", student);
		return map;
	}

	//UPDATE STUDENT
	@RequestMapping(value = "/update_student/{id}", method = RequestMethod.POST)
	public String updateStudent(@PathVariable Integer id, @ModelAttribute Student student) {
		// www-for-urlencoded la value pass pannanum
		
		studentDao.updateStudent(id,student);
		return "Updated Successfully";
	}

}
