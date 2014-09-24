/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.perconsys.entities;

import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Less
 */
public class Blog {
	
	private long id;
	
	@NotEmpty(message="Empty name of blog")
	private String name;
	
	@NotEmpty(message="Empty url alias")
	private String url_name;
	
	private User user = null;
	
	public Blog(){}
	
	public Blog(long id){
		this.id= id;
	}
	
	public Blog(String name, String url_name){
		this.name = name;
		this.url_name = url_name;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the url_name
	 */
	public String getUrl_name() {
		return url_name;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param url_name the url_name to set
	 */
	public void setUrl_name(String url_name) {
		this.url_name = url_name;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}
}
