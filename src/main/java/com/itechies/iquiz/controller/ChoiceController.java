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
import com.itechies.iquiz.dao.ChoiceDAO;
import com.itechies.iquiz.model.Choice;
@RestController
public class ChoiceController {
	
	@Autowired
	ChoiceDAO choiceDao;

	//ADD CHOICE
	@RequestMapping(value = "/add_choice", method = RequestMethod.POST)
	public String addChoice(@ModelAttribute Choice choice) {
		String name = choiceDao.addChoice(choice);
		return name +" Added SuccessFully";
	}

	//VIEW CHOICE
	@ResponseBody
	@RequestMapping(value = "/view_choices", method = RequestMethod.GET)
	public Map<String, Object> viewChoice() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("students", choiceDao.fetchAllChoices());
		return map;

	}

	//DELETE CHOICE
	@RequestMapping(value = "/delete_Choice/{id}", method = RequestMethod.DELETE)
	public String deleteChoice(@PathVariable Integer id) {
		choiceDao.deleteChoice(id);
		return "Deleted SuccessFully";

	}

	//FETCH ONE CHOICE RECORD
	// doubt yen student ah value ah anupa mudila??
	//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) ithu add pannanum model class la
	@ResponseBody
	@RequestMapping(value = "/fetch_choice/{id}", method = RequestMethod.GET)
	public Map<String, Choice> fetchChoice(@PathVariable Integer id) {
		Choice student=choiceDao.fetchChoiceDetail(id);
		Map<String, Choice> map = new HashMap<String, Choice>();
		map.put("student", student);
		return map;
	}

	//UPDATE CHOICE
	@RequestMapping(value = "/update_choice/{id}", method = RequestMethod.POST)
	public String updateChoice(@PathVariable Integer id, @ModelAttribute Choice choice) {
		choiceDao.updateChoice(id,choice);
		return "Updated Successfully";
	}

}
