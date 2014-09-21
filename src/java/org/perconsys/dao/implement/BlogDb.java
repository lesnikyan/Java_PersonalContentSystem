/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.perconsys.dao.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.perconsys.dao.BlogDao;
import org.perconsys.entities.Blog;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

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
	public Blog getById(int id) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public Blog create(final Blog blog) {
		KeyHolder keyh = new GeneratedKeyHolder();
		final String sql = "insert into blogs (name, url_name) value (?, ?) ;";
		
		getJdbcTemplate().update(
		new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
				PreparedStatement pstat = conn.prepareStatement(sql, new String[]{"id"});
				pstat.setString(1, blog.getName());
				pstat.setString(2, blog.getUrl_name());
				return pstat;
			}
		}
		, keyh);
		Integer id = (Integer)keyh.getKey();
		blog.setId(id);
		return blog;
	}

	@Override
	public void update(Blog blog) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public void delete(int id) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
	
}
