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
import org.perconsys.dao.PostDao;
import org.perconsys.entities.Blog;
import org.perconsys.entities.Post;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

/**
 *
 * @author Less
 */
public class PostDb  extends JdbcDaoSupport implements PostDao{

	@Override
	public Post create(final Post post) {
		KeyHolder keyh = new GeneratedKeyHolder();
		final String sql = "insert into `posts` (`title`, `content`, `blog_id`, `date`) value (?, ?, ?, now()) ;";
		
		getJdbcTemplate().update(
		new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
				PreparedStatement pstat = conn.prepareStatement(sql, new String[]{"id"});
				pstat.setString(1, post.getTitle());
				pstat.setString(2, post.getContent());
				pstat.setLong(3, post.getBlog().getId());
				return pstat;
			}
		}
		, keyh);
		Long id = (Long)keyh.getKey();
		post.setId(id);
		return post;
	}

	@Override
	public Post getById(long id) {
		List<Post> res = getJdbcTemplate().query(
				"select * from `posts` where `id` = ? ;",
				new PostMapper()
				, id
		);
		if(res.isEmpty()){
			return null;
		}
		return res.get(0);
	}

	@Override
	public boolean update(Post post) {
		String sql = "update `posts` set "
				+ "title = ?, "
				+ "content = ? "
				+ "where `id` = ? ; ";
		return getJdbcTemplate().update(sql, 
				post.getTitle(),
				post.getContent(),
				post.getId()
				) > 0;
	}

	@Override
	public boolean delete(long id) {
		return getJdbcTemplate().update(
				"delete from posts where id = ? ",
				id
		) > 0;
	}

	/**
	 * 
	 * @param blog
	 * @param from
	 * @param count
	 * @param order - ASC = true, DESC = false
	 * @return 
	 */
	@Override
	public List<Post> getList(Blog blog, int from, int count, boolean order) {
		
		return getJdbcTemplate().query(
				String.format("select * from `posts` "
						+ " where blog_id = ? "
						+ " order by id %s limit %d, %d ", 
						(order ? "ASC" : "DESC"), from, count),
				new PostMapper(),
				blog.getId()
				);
	}
	
	@Override
	public List<Post> getLast(Blog blog, int count) {
		return getList(blog, 0, count, false);
	}
	
	class PostMapper  implements ParameterizedRowMapper<Post> {
		@Override
		public Post mapRow(ResultSet rs, int i) throws SQLException {
			Post post = new Post();
			post.setId(rs.getLong("id"));
			post.setTitle(rs.getString("title"));
			post.setContent(rs.getString("content"));
			post.setDate(rs.getDate("date"));
			post.setBlog(new Blog(rs.getLong("blog_id")));
			return post;
		}
	}
	
}
