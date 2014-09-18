/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.perconsys.entities;

import javax.validation.constraints.*;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;


/**
 *
 * @author Less
 */
public class User {
	private Long id;
	
	
	@NotEmpty(message="Empty name")
	@Size(min = 3, max = 32, message="Incorrect size of name")
	@Pattern(regexp = "^[\\w\\d\\_\\- ]+$", message="Incorrect characters")
	private String name;
	
	@NotEmpty( message="Empty login")
	@Size(min = 3, max = 32, message="Incorrect login size")
	@Pattern(regexp="^[0-9a-z\\_\\-]+$", message="Incorrect login")
	private String login;
	
	@NotEmpty (message="Empty name")
	@Size(min=8, max=32, message = "Incorrect size of password")
	@Pattern(regexp="^[0-9a-z\\-_!$%*,.?]+$", message="Incorrect symbols of password")
	private String password;
	
	@NotEmpty( message="Empty email")
	@Email( message="Incorrect email format")
	private String email;
	
	private String authKey="";
	
	public User(){}
	
	public User(String name, String login, String password, String email, String authKey){
		this.name = name;
		this.login = login;
		this.password  = password;
		this.email = email;
		this.authKey = authKey;
	}
	
	public User(Long id){
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @return the authKey
	 */
	public String getAuthKey() {
		return authKey;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param login the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @param authKey the authKey to set
	 */
	public void setAuthKey(String authKey) {
		this.authKey = authKey;
	}
	
}
