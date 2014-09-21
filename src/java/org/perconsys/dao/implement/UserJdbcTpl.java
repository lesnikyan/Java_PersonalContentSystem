/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.perconsys.dao.implement;

import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.apache.commons.codec.digest.Crypt;
import org.apache.commons.codec.digest.Sha2Crypt;
import org.perconsys.dao.UserDao;
import org.perconsys.entities.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

/**
 *
 * @author Less
 */
public class UserJdbcTpl extends JdbcDaoSupport implements UserDao {
	
	private String table;
	private String keySalt;
	
	public void setTable(String value){
		table = value;
	}
	
	public void setKeySalt(String value){
		keySalt = value;
	}

	/**
	 * @return the table
	 */
	public String getTable() {
		return table;
	}

	/**
	 * @return the keySalt
	 */
	public String getKeySalt() {
		return keySalt;
	}
	
	@Override
	public void test(){
		//JdbcDaoSupport t = new JdbcDaoSupport();
		JdbcTemplate dbtpl = this.getJdbcTemplate();
		System.out.println(dbtpl.getClass());
		System.out.println("UserDAO:test");
		System.out.println(dbtpl.getClass());
	}

	@Override
	public User add(final User user) {
		//JdbcTemplate dbtpl = this.getJdbcTemplate();
		
//		return dbtpl.update("insert into `users` (`name`, `login`, `password`, `email`, `authKey`) values (?, ?, ?, ?, ?)",
//			user.getName(),
//			user.getLogin(),
//			user.getPassword(),
//			user.getEmail(),
//			""
//				);
		
		KeyHolder keyh = new GeneratedKeyHolder();
		final String sql = String.format("insert into `%s` (`name`, `login`, `password`, `email`, `authKey`) "
				+ "values (?, ?, ?, ?, ?) ;", table);
		
		PreparedStatementCreator statcr = new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
				PreparedStatement pstat = conn.prepareStatement(sql, new String[]{"id"});
				pstat.setString(1, user.getName());
				pstat.setString(2, user.getLogin());
				pstat.setString(3, hash(user.getPassword()));
				pstat.setString(4, user.getEmail());
				pstat.setString(5, "");
				return pstat;
			}
		};
		getJdbcTemplate().update(statcr, keyh);
		Long id = (Long) keyh.getKey();
		user.setId(id);
		return user;
	}

	@Override
	public boolean update(User user) {
		return getJdbcTemplate().update(String.format("update `%s` set "
				+ "name = ?, login = ?, password = ?, email = ? "
				+ "where id = ?", table), 
				user.getName(), user.getLogin(), hash(user.getPassword()), user.getEmail(), user.getId())
				> 0;
	}

	@Override
	public boolean updateAuthKey(User user) {
		return getJdbcTemplate().update(String.format("update `%s` set "
				+ "authKey = ? "
				+ "where id = ?", table), 
				user.getName(), user.getLogin(), hash(user.getPassword()), user.getEmail(), user.getId())
				> 0;
	}

	@Override
	public User getById(long id) {
		List<User> res = getJdbcTemplate().query(String.format("select * from `%s` where `id` = ? ;", table), 
				new UserMapper(), id);
		if(res.isEmpty()){
			return null;
		}
		return res.get(0);
	}
	
	@Override
	public User checkByLogin(String login, String password){
		final String sql = String.format("select * from `%s` where login = ? AND password = ? ;", table);
		List<User> res = getJdbcTemplate().query(sql, new UserMapper(), login, hash(password));
		if(res.isEmpty())
			return null;
		return res.get(0);
	}

	@Override
	public List<User> list(Long from, Long count) {
		JdbcTemplate dbtpl = this.getJdbcTemplate();
		String sql = String.format("select * from `%s` LIMIT ?, ? ;", table);
		return dbtpl.query(
			sql, 
			new Object[]{from, count}, 
			new UserMapper()
		);
	}

	@Override
	public List<User> listByName(String nameTpl) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public List<User> listByEmail(String emailTpl) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public int delete(long id) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public int delete(User user) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
	
	@Override
	public String hash(String src){
		return Sha2Crypt.sha512Crypt(src.getBytes(), getKeySalt());
	}

	@Override
	public User getByKey(String key) {
		JdbcTemplate dbtpl = this.getJdbcTemplate();
		String sql = String.format("select * from `%s` WHERE `authKey` = ? LIMIT 0, 1 ;", table);
		return dbtpl.queryForObject(
			sql, 
			new Object[]{key}, 
			new UserMapper()
		);
	}
	
	class UserMapper implements ParameterizedRowMapper<User> {
		@Override
		public User mapRow(ResultSet rs, int i) throws SQLException {
			User user = new User();
			user.setId(rs.getLong("id"));
			user.setName(rs.getString("name"));
			user.setLogin(rs.getString("login"));
			user.setPassword(rs.getString("password"));
			user.setEmail(rs.getString("email"));
			user.setAuthKey(rs.getString("authKey"));
			return user;
		}
	}
}
