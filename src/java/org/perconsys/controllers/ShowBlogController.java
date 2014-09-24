/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.perconsys.controllers;

import java.util.List;
import org.perconsys.dao.BlogDao;
import org.perconsys.dao.CommentDao;
import org.perconsys.dao.PostDao;
import org.perconsys.dao.UserDao;
import org.perconsys.entities.Blog;
import org.perconsys.entities.Comment;
import org.perconsys.entities.Post;
import org.perconsys.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Less
 */
@Controller
public class ShowBlogController  extends BasicController {
	@Autowired
	private BlogDao blogDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private PostDao postDao;
	@Autowired
	private CommentDao commDao;
	
	@RequestMapping({"/blog/{name}"})
	public String viewBlogDefault(@PathVariable("name") String name, ModelMap model){
		return blogViewPaged(name, 1, model);
	}
	
	@RequestMapping({"/blog/{name}/{page}"})
	public String viewBlog(@PathVariable("name") String name, @PathVariable("page") int page, ModelMap model){
		return blogViewPaged(name, page, model);
	}
	
	private String blogViewPaged(String name, int page, ModelMap model){
		
		User user = currentUser();
		String numRgx = "^[\\d]{1,10}$";
		Blog blog;
		if(name.matches(numRgx)){ // if number
			// get Blog by id
			blog = blogDao.getById(Long.parseLong(name));
		} else { // if name
			// get blog by name
			blog = blogDao.getByName(name);
		}
		int count = 10;
		List<Post> posts = postDao.getList(blog, (page-1)*count, count, false);
		if(blog == null){
			// not found
		}
		model.addAttribute("blog", blog);
		model.addAttribute("authorized", (user != null));
		model.addAttribute("posts", posts);
		
		return "blog/posts";
	}
	
	@RequestMapping(value="/post/{name}")
	public String viewPost(@PathVariable("name") long name, ModelMap model){
		Post post = postDao.getById(name);
		User user = currentUser();
		Blog blog = blogDao.getById(post.getBlog().getId());
		post.setBlog(blog);
		List<Comment> comments = commDao.getByPost(post);
		
		model.addAttribute("post", post);
		model.addAttribute("blog", blog);
		model.addAttribute("user", user);
		model.addAttribute("authorized", user != null);
		model.addAttribute("comments", comments);
		return "post/view";
	}
}
