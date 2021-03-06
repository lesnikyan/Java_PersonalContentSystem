/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.perconsys.controllers;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.perconsys.dao.UserDao;
import org.perconsys.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Less
 */
@Controller
@RequestMapping("/auth")
public class AuthController extends BasicController {
	
	@Autowired
//	private UserJdbcTpl dao;
	private UserDao dao;
	
	private final String basePath = "/auth";
	
	@Value("#{properties['base.webpath']}")
	private String baseWebpath;
	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public ModelAndView registerForm(ModelMap model){
		
		return new ModelAndView("auth/reg_form", "user", new User());
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String registerHandler(@Valid @ModelAttribute("user")  User user, 
			BindingResult result, 
			HttpServletRequest request){
		if(result.hasErrors()){
			return "auth/reg_form";
		}
		user = dao.add(user);
		setToSession("user", user);
		return "auth/reg_result";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public ModelAndView loginForm(@RequestParam(value = "incorrect", required = false) String incorrect, ModelMap model){
		model.addAttribute("incorrect", (incorrect != null ? incorrect.equalsIgnoreCase("yes") : false) );
		ModelAndView mv = new ModelAndView("auth/login_form", "user", new User());
		return mv;
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String loginHandler(@RequestParam("login") String login,
			@RequestParam("password") String password,
			ModelMap model){
		User user = dao.checkByLogin(login, password);
		if(user == null || user.getId() == null){
			model.addAttribute("incorrect", "yes");
			model.addAttribute("form", login+"-"+password);
			model.addAttribute("nouser", (user == null ? "null" : user.getName()));
			return "redirect:/auth/login";
		}
		setToSession("user", user);
		return "redirect:/";
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
	
}
