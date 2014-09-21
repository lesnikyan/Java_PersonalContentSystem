/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.perconsys.controllers;

import java.util.List;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import org.perconsys.dao.BlogDao;
import org.perconsys.dao.UserDao;
import org.perconsys.entities.Blog;
import org.perconsys.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
	
	
	@RequestMapping({""})
	public String myBlog(ModelMap model){
		User user = (User) getFromSession("user");
		// guest view
		if(user == null){
			return "blog/main";
		}
		// user view
		List<Blog> blogs = blogDao.blogsByUser(user);
		model.addAttribute("blogs", blogs);
		return "blog/list";
	}
	
	@RequestMapping(value={"/test"}, method=RequestMethod.GET)
	public String testMapping(){
		return "blog/main";
	}
	
	@RequestMapping({"/view/{name}"})
	public String viewBlog(@PathVariable String name, ModelMap model){
		String numRgx = "^[\\d]{1,10}$";
		Blog blog;
		if(name.matches(numRgx)){ // if number
			// get Blog by id
			blog = blogDao.getById(Long.parseLong(name));
		} else { // if name
			// get blog by name
			blog = blogDao.getByName(name);
		}
		if(blog == null){
			// not found
		}
		model.addAttribute("blog", blog);
		
		return "blog/posts";
	}
	
	@RequestMapping(value={"/create"}, method=RequestMethod.GET)
	public ModelAndView createForm(){
		User user = ((User)getFromSession("user"));
		if(user == null){
			// trying login by cookie
			Cookie authCookie = cookies.get("authKey");
			if(authCookie != null){
				String authKey = authCookie.getValue();
				user = userDao.getByKey(authKey);
			}
			// if no user - redirect to login page
			if(user == null){
				return new ModelAndView("auth/login_form", "user", new User());
			}
		}
		
		Blog blog = new Blog("new blog", "");
		blog.setUser(user);
		blog = blogDao.create(blog);
		return new ModelAndView("blog/edit_form", "blog", blog);
	}
	
	@RequestMapping(value={"edit/{id}"}, method=RequestMethod.GET)
	public ModelAndView editForm(@PathVariable("id") long id,
			HttpServletRequest request){
		
		User user = ((User)request.getSession(true).getAttribute("user"));
		if(user == null){
			// trying login by cookie
			Cookie authCookie = cookies.get("authKey");
			if(authCookie != null){
				String authKey = authCookie.getValue();
				user = userDao.getByKey(authKey);
			}
			// if no user - redirect to login page
			if(user == null){
				return new ModelAndView("auth/login_form", "user", new User());
			}
		}
		
		Blog blog = blogDao.getById(id);
		if(blog == null){
			// if no blog bi this id
			return new ModelAndView("redirect:/blog/create");
		} else {
			if(user.getId() != blog.getUser().getId()){
				// if user is not an owner
				return new ModelAndView("redirect:/blog/view/"+id);
			}
		}
		blog.setUser(user);
		return new ModelAndView("blog/edit_form", "blog", blog);
	}
	
	@RequestMapping(value="edit", method=RequestMethod.POST)
	public String editHandler(@ModelAttribute("blog") Blog blogData, ModelMap model){
		User user = (User) getFromSession("user");
		if(user == null){
			return "redirect:/auth/login";
		}
		Blog blog = blogDao.getById(blogData.getId());
		// no same blog
		if(blog == null){
			return "page404";
		}
		// current user is not an owner of blog
		if(blog.getUser().getId() != user.getId()){
			return "redirect:/blog/view/"+blogData.getId();
		}
		blogDao.update(blogData, user);
		
		return "redirect:/blog/view/" + blogData.getId();
	}
	
}
