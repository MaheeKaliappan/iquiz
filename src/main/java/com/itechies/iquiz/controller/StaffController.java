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

import com.itechies.iquiz.dao.StaffDAO;
import com.itechies.iquiz.model.Staff;


@RestController
public class StaffController {
	
	@Autowired
	StaffDAO staffDao;

	//ADD STAFF
	@RequestMapping(value = "/add_staff", method = RequestMethod.POST)
	public String addStaff(@ModelAttribute Staff staff) {
		String name = staffDao.addStaff(staff);
		return name +" Added SuccessFully";
	}

	//VIEW STAFF
	@ResponseBody
	@RequestMapping(value = "/view_staffs", method = RequestMethod.GET)
	public Map<String, Object> viewStaff() {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("staffs", staffDao.fetchAllStaff());
		return map;

	}

	//DELETE STAFF
	@RequestMapping(value = "/delete_staff/{id}", method = RequestMethod.DELETE)
	public String deleteStaff(@PathVariable Integer id) {
		System.out.println(id);
		staffDao.deleteStaff(id);
		return "Deleted SuccessFully";

	}

	//FETCH ONE STAFF RECORD
	// doubt yen student ah value ah anupa mudila??
	//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) ithu add pannanum model class la
	@ResponseBody
	@RequestMapping(value = "/fetch_staff/{id}", method = RequestMethod.GET)
	public Map<String, Staff> fetchStudent(@PathVariable Integer id) {

		
		Staff staff =staffDao.fetchStaffDetail(id);
		Map<String, Staff> map = new HashMap<String, Staff>();
		map.put("student", staff);
		return map;
	}

	//UPDATE STAFF
	@RequestMapping(value = "/update_staff/{id}", method = RequestMethod.POST)
	public String updateStaff(@PathVariable Integer id, @ModelAttribute Staff staff) {
		// www-for-urlencoded la value pass pannanum
		System.out.println(id);
		staffDao.updateStaff(id,staff);
		return "Updated Successfully";
	}

}
