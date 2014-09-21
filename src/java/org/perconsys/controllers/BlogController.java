/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.perconsys.controllers;

import javax.servlet.http.HttpServletRequest;
import org.perconsys.dao.BlogDao;
import org.perconsys.dao.UserDao;
import org.perconsys.entities.Blog;
import org.perconsys.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Less
 */
@Controller
@RequestMapping("/blog")
public class BlogController extends BasicController {
	@Autowired
	private BlogDao blogDao;
	@Autowired
	private UserDao userDao;
	
	@RequestMapping({"/{name}", ""})
	public String mainPage(@PathVariable String name, ModelMap model){
		String numRgx = "[^\\d+$]{1,10}";
		Blog blog = null;
		if(name.matches(numRgx)){ // if number
			// get Blog by id
			blog = blogDao.getById(Integer.parseInt(name));
		} else { // if name
			// get blog by name
			blog = blogDao.getByName(name);
		}
		if(blog == null){
			// not found
		}
		model.addAttribute("blog", blog);
		
		return "blog/main";
	}
	
	@RequestMapping("/{id}/edit")
	public ModelAndView editForm(@PathVariable("id") int id, @CookieValue("authKey") String authKey,
			HttpServletRequest request){
		
		User user = ((User)request.getSession(true).getAttribute("user"));
		if(user == null){
			// trying login by cookie
			user = userDao.getByKey(authKey);
			// if no user - redirect to login page
			if(user == null){
				return new ModelAndView("auth/login_form", "user", user);
			}
		}
		
		Blog blog = null;
		blog = blogDao.getById(id);
		if(blog == null){
			blog = blogDao.create(new Blog());
			blog.setUser(user);
		}
		return new ModelAndView("blog/form", "blog", blog);
	}
	
	@RequestMapping("/edit")
	public String editHandler(){
		
		return "blog/main";
	}
	
	@RequestMapping("/{id}")
	public String blogContent(@PathVariable int id){
		
		return "blog/posts";
	}
	
}
