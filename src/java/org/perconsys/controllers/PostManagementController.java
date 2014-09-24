/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.perconsys.controllers;

import org.perconsys.dao.PostDao;
import org.perconsys.entities.Blog;
import org.perconsys.entities.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Less
 */

@Controller
@RequestMapping("/editpost")
public class PostManagementController extends BasicController {
	
	@Autowired
	private PostDao dao;
	
	@RequestMapping(value="/create", method=RequestMethod.GET)
	public ModelAndView create(){
		Post post = new Post();
		String blogId = getRequest().getParameter("blog");
		ModelAndView model = new ModelAndView("post/form", "post", post);
		model.addObject("blogId", blogId);
		return model;
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
	public ModelAndView create(@PathVariable("id") long id){
		
		return new ModelAndView("post/form", "post", new Post());
	}
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public String editHandler(@ModelAttribute("post") Post post, 
			@RequestParam(value="blog_id") Long blogId,
			ModelMap model){
		Blog blog = new Blog();
		blog.setId(blogId);
		post.setBlog(blog);
		if(post.getId() == 0){
			//create
			dao.create(post);
		} else {
			// update
			dao.update(post);
		}
		return "redirect:/post/" + post.getId();
	}
	
	
	
}
