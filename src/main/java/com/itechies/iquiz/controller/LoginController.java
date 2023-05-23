package com.itechies.iquiz.controller;


import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;



@RestController
public class LoginController {
	

	
	@RequestMapping(value = "/home", method = RequestMethod.GET	)
//	public ModelAndView login(HttpServletRequest request, @RequestParam("userName") String userName) {
		public ModelAndView login(HttpServletRequest request, @RequestParam("userName") String userName) {
		System.out.println("home");
		
		HttpSession session= request.getSession();
		session.setAttribute("userName", userName);
		System.out.println(session.getAttribute("userName")+" userNAMEEE");
		ModelAndView modelAndView = new ModelAndView("home");
		
		
		return modelAndView;
	}
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView home() {
		
		ModelAndView modelAndView = new ModelAndView("login");
		System.out.println("login");
		
		return modelAndView;
	}
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest req) {
		HttpSession session= req.getSession();
		session.removeAttribute("userName");
		System.out.println(session.getAttribute("userName")+" userNAMEEE");
		ModelAndView modelAndView = new ModelAndView("login");
		
		
		return modelAndView;
	}
	


}
