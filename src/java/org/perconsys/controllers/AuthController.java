/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.perconsys.controllers;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.perconsys.dao.UserDao;
import org.perconsys.dao.implement.UserJdbcTpl;
import org.perconsys.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Less
 */
@Controller
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
//	private UserJdbcTpl dao;
	private UserDao dao;
	
	private final String basePath = "/auth";
	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public ModelAndView registerForm(ModelMap model){
		
		return new ModelAndView("auth/reg_form", "user", new User());
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST) // @ModelAttribute("user") //  ModelMap model,
	public String registerHandler(@Valid @ModelAttribute("user")  User user, BindingResult result){
		if(result.hasErrors()){
			// handle errors
			//model.addAttribute("user", user);
			return "auth/reg_form";
		}
	//	model.addAttribute("user", user);
		dao.save(user);
		
		return "auth/reg_result";
	}
	
	@RequestMapping({"", "/info", "/default"})
	public String defaultPage(@CookieValue String authKey, HttpServletRequest request, ModelMap model){
		// check authKey
		model.addAttribute("basePath", request.getContextPath() + basePath);
		return "auth/default";
	}
	
	// test method
	@RequestMapping({"/users"})
	public String test_userList(ModelMap model){
		model.addAttribute("users", dao.list(0L, 10L));
		return "auth/test-userlist";
	}
	
	/**
	 * validate user's fields given from http request
	 * @param user 
	 */
	private boolean validateUser(User user){
		
		if(1==1){
			
		}
		
		return true;
	}
	
}
