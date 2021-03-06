/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.perconsys.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.perconsys.entities.User;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 *
 * @author Less
 */
public class BasicController {
	
	Map<String, Cookie> cookies = new HashMap<>();
	
	public BasicController(){
//		Cookie[] cookieArr = getRequest().getCookies();
//		for(Cookie c: cookieArr){
//			cookies.put(c.getName(), c);
//		}
	}
	
	public User currentUser(){
		return (User)getFromSession("user");
	}
	
	public static HttpServletRequest getRequest(){
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
	}
	
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
