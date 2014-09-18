/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.perconsys.dao.implement;

import java.security.MessageDigest;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.apache.commons.codec.digest.Crypt;
import org.apache.commons.codec.digest.Sha2Crypt;
import org.perconsys.dao.UserDao;
import org.perconsys.entities.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

/**
 *
 * @author Less
 */
public class UserJdbcTpl extends JdbcDaoSupport implements UserDao {
	
	private String table = "users";
	private String keySalt = "12345";
	
	public void setTable(String value){
		table = value;
	}
	
	public void setKeySalt(String value){
		keySalt = value;
	}
	
	
	public int add(User user){
		return 0;
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
	public int save(User user) {
		JdbcTemplate dbtpl = this.getJdbcTemplate();
		
		return dbtpl.update("insert into `users` (`name`, `login`, `password`, `email`, `authKey`) values (?, ?, ?, ?, ?)",
			user.getName(),
			user.getLogin(),
			user.getPassword(),
			user.getEmail(),
			""
				);
	}

	@Override
	public User getById(long id) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
	
	public String hash(String src){
		return Sha2Crypt.sha512Crypt(src.getBytes(), keySalt);
	}
	
	public String encode(String src){
		return "";
	}
	public String decode(String src){
		return "";
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
