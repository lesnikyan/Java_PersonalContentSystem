/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.perconsys.dao;

import java.util.List;
import org.perconsys.entities.Comment;
import org.perconsys.entities.Post;

/**
 *
 * @author Less
 */
public interface CommentDao {
	public List<Comment> getByPost(Post post);
	public Comment create(Comment comment);
}
