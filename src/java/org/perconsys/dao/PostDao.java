/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.perconsys.dao;

import java.util.List;
import org.perconsys.entities.Blog;
import org.perconsys.entities.Post;

/**
 *
 * @author Less
 */
public interface PostDao {
	public Post create(Post post);
	public Post getById(long id);
	public boolean update(Post post);
	public boolean delete(long id);
	public List<Post> getList(Blog blog, int from, int count, boolean order);
	public List<Post> getLast(Blog blog, int count);
}
