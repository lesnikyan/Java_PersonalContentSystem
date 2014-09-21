/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.perconsys.dao;

import java.util.List;
import org.perconsys.entities.User;

/**
 *
 * @author Less
 */
public interface UserDao {
	public User add(final User user);
	public boolean update(User user);
	public boolean updateAuthKey(User user);
	public User getById(long id);
	public User getByKey(String key);
	public User checkByLogin(String login, String password);
	public List<User> list(Long from, Long count);
	public List<User> listByName(String nameTpl);
	public List<User> listByEmail(String emailTpl);
	public int delete(long id);
	public int delete(User user);
	public String hash(String data);
	
	public void test();
}
