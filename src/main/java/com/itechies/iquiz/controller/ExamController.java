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
import com.itechies.iquiz.dao.ExamDAO;
import com.itechies.iquiz.model.Exam;



@RestController
public class ExamController {
	
	@Autowired
	ExamDAO examDao;

	//ADD EXAM
	@RequestMapping(value = "/add_exam", method = RequestMethod.POST)
	public String addExam(@ModelAttribute Exam exam) {
		String name = examDao.addExam(exam);
		return name +" Added SuccessFully";
	}

	//VIEW EXAM
	@ResponseBody
	@RequestMapping(value = "/view_exams", method = RequestMethod.GET)
	public Map<String, Object> viewExam() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("exams", examDao.fetchAllExams());
		return map;

	}

	//DELETE EXAM
	@RequestMapping(value = "/delete_exam/{id}", method = RequestMethod.DELETE)
	public String deleteExam(@PathVariable Integer id) {
		examDao.deleteExam(id);
		return "Deleted SuccessFully";

	}

	//FETCH ONE EXAM RECORD
	// doubt yen student ah value ah anupa mudila??
	//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) ithu add pannanum model class la
	@ResponseBody
	@RequestMapping(value = "/fetch_exam/{id}", method = RequestMethod.GET)
	public Map<String, Exam> fetchExam(@PathVariable Integer id) {
		Exam exam =examDao.fetchExamDetail(id);
		Map<String, Exam> map = new HashMap<String, Exam>();
		map.put("exam", exam);
		return map;
	}

	//UPDATE EXAM
	@RequestMapping(value = "/update_exam/{id}", method = RequestMethod.POST)
	public String updateExam(@PathVariable Integer id, @ModelAttribute Exam exam) {
		// www-for-urlencoded la value pass pannanum
		
		examDao.updateExam(id,exam);
		return "Updated Successfully";
	}

	
}
