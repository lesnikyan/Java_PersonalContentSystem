/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.perconsys.dao;

import org.perconsys.entities.Blog;

/**
 *
 * @author Less
 */
public interface BlogDao {
	public Blog getByName(String name);
	public Blog getById(int id);
	public Blog create(Blog blog);
	public void update(Blog blog);
	public void delete(int id);
}
