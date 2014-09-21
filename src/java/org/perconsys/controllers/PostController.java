/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.perconsys.controllers;

import org.perconsys.entities.Post;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Less
 */

@RequestMapping("/post")
public class PostController extends BasicController {
	
	@RequestMapping(value="/create")
	public ModelAndView create(){
		
		return new ModelAndView("post/form", "post", new Post());
	}
	
	@RequestMapping(value="/edit/{id}")
	public ModelAndView create(@PathVariable("id") long id){
		
		return new ModelAndView("post/form", "post", new Post());
	}
	
	public String editHandler(@ModelAttribute("blog") Post postData, ModelMap model){
		
		return "post/saved";
	}
	
	@RequestMapping(value="/view/{id}")
	public String viewPost(@PathVariable("id") long id){
		
		return "post/view";
	}
	
	
}
