/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.perconsys.controllers;

import org.perconsys.entities.User;
import org.springframework.stereotype.*;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Less
 */
@Controller
public class Main extends BasicController {
	
	@RequestMapping({"/", "/index"})
	public String welcomPage(ModelMap model){
		model.addAttribute("basePath", "");
		User user = (User) getFromSession("user");
		model.addAttribute("loggedin", user != null);
		if(user != null){
			model.addAttribute("user", user);
		}
		return "welcome";
	}
	
}
