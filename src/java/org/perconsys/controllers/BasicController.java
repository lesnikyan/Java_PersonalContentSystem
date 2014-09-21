/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.perconsys.controllers;

import javax.servlet.http.HttpSession;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 *
 * @author Less
 */
public class BasicController {
	
	public static HttpSession getSession(){
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		return attr.getRequest().getSession(true);
	}
	
	public static Object getFromSession(String key){
		return getSession().getAttribute(key);
	}
	
	public static void setToSession(String key, Object attribute){
		getSession().setAttribute(key, attribute);
	}
}
