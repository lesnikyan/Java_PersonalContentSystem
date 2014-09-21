/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.perconsys.dao;

import java.util.List;
import org.perconsys.entities.Blog;
import org.perconsys.entities.User;

/**
 *
 * @author Less
 */
public interface BlogDao {
	public Blog getByName(String name);
	public Blog getById(long id);
	public List<Blog> blogsByUser(User user);
	public Blog getByIdUser(long id, User user);
	public Blog create(Blog blog);
	public boolean update(Blog blog, User user);
	public void delete(long id);
}
