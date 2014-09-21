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
import org.perconsys.dao.BlogDao;
import org.perconsys.entities.Blog;
import org.perconsys.entities.User;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.web.servlet.support.JstlUtils;

/**
 *
 * @author Less
 */
public class BlogDb extends JdbcDaoSupport implements BlogDao {

	@Override
	public Blog getByName(String name) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public Blog getByIdUser(long id, User user) {
		String sql = "select * from blogs where id = ? AND user_id = ? ;";
		List<Blog> res = getJdbcTemplate().query(sql, new BlogMapper(), id, user.getId());
		if(res.isEmpty()){
			return null;
		}
		Blog blog = res.get(0);
		blog.setUser(user);
		return blog;
	}

	@Override
	public Blog getById(long id){
		String sql = "select * from blogs where id = ? ;";
		List<Blog> res = getJdbcTemplate().query(sql, new BlogMapper(), id);
		if(res.isEmpty()){
			return null;
		}
		return res.get(0);
	}
	
	@Override
	public List<Blog> blogsByUser(User user){
		String sql = "select * from blogs where user_id = ? ;";
		List<Blog> res = getJdbcTemplate().query(sql, new BlogMapper(), user.getId());
		if(res.isEmpty()){
			return null;
		}
		for(Blog blog: res){
			blog.setUser(user);
		}
		return res;
	}

	@Override
	public Blog create(final Blog blog) {
		KeyHolder keyh = new GeneratedKeyHolder();
		final String sql = "insert into blogs (name, url_name, user_id) value (?, ?, ?) ;";
		
		getJdbcTemplate().update(
		new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
				PreparedStatement pstat = conn.prepareStatement(sql, new String[]{"id"});
				pstat.setString(1, blog.getName());
				pstat.setString(2, blog.getUrl_name());
				pstat.setLong(3, blog.getUser().getId());
				return pstat;
			}
		}
		, keyh);
		Long id = (Long)keyh.getKey();
		blog.setId(id);
		return blog;
	}

	@Override
	public boolean update(Blog blog, User user) {
		String sql = "update blogs set "
				+ "name = ?, url_name = ? "
				+ "where id = ? AND user_id = ? ";
		return getJdbcTemplate().update(sql, 
				blog.getName(), blog.getUrl_name(),
				blog.getId(), user.getId()
			) > 0;
	}

	@Override
	public void delete(long id) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
	
	class BlogMapper implements ParameterizedRowMapper<Blog> {
		@Override
		public Blog mapRow(ResultSet rs, int i) throws SQLException {
			Blog blog = new Blog();
			blog.setId(rs.getLong("id"));
			blog.setName(rs.getString("name"));
			blog.setUrl_name(rs.getString("url_name"));
			User user = new User();
			user.setId(rs.getLong("user_id"));
			blog.setUser(user);
			return blog;
		}
	}
	
}
