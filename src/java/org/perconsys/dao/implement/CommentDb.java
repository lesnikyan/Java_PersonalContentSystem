/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.perconsys.dao.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.perconsys.dao.CommentDao;
import org.perconsys.entities.Comment;
import org.perconsys.entities.Post;
import org.perconsys.entities.User;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

/**
 *
 * @author Less
 */
public class CommentDb extends JdbcDaoSupport implements CommentDao {

	@Override
	public List<Comment> getByPost(Post post) {
		return getJdbcTemplate().query(
//				"select comments.*, users.name as u_name from `comments` "
//						+ " left inner join users on users.id = comments.user_id "
//						+ " where `comments`.`post_id` = ? order by `comments`.`id` DESC limit 0, 100 ; "
//				+
				"select c.*, u.name as u_name \n" +
						"from `comments` c \n" +
						"join `users` u on u.id = c.user_id \n" +
						"where c.`post_id` = ? \n" +
						"order by c.`id` DESC \n" +
						"limit 0, 100 ;"
				, 
				new CommentMapper(), 
				post.getId());
	}

	@Override
	public Comment create(final Comment comm) {
		KeyHolder keyh = new GeneratedKeyHolder();
		final Long target = comm.getTargetId() == 0 ? null : comm.getTargetId();
		final String sql = "insert into blogs (`title`, `content`, `post_id`, `user_id`, `target_id`, `date`) "
				+ "value (?, ?, ?, ?, ?, now()) ;";
		
		getJdbcTemplate().update(
		new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
				PreparedStatement pstat = conn.prepareStatement(sql, new String[]{"id"});
				pstat.setString(1, comm.getTitle());
				pstat.setString(2, comm.getContent());
				pstat.setLong(3, comm.getPostId());
				pstat.setLong(4, comm.getUser().getId());
				pstat.setLong(5, target);
				return pstat;
			}
		}
		, keyh);
		Long id = (Long)keyh.getKey();
		comm.setId(id);
		return comm;
	}
	
	class CommentMapper implements ParameterizedRowMapper<Comment> {
		
		@Override
		public Comment mapRow(ResultSet rs, int i) throws SQLException {
			Comment comm = new Comment();
			comm.setId(rs.getLong("id"));
			comm.setTitle(rs.getString("title"));
			comm.setContent(rs.getString("content"));
			comm.setTargetId(rs.getLong("target_id"));
			comm.setDate(rs.getDate("date"));
			
			User user = new User(rs.getLong("user_id"));
			user.setName("u_name");
			comm.setUser(user);
			
			return comm;
		}
	}
	
}
