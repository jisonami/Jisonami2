package org.jisonami.dao.impl.jdbctemplate;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbcp.BasicDataSource;
import org.jisonami.dao.IUserDao;
import org.jisonami.entity.User;
import org.jisonami.util.DBUtils;
import org.jisonami.util.StringUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDao implements IUserDao {

	private JdbcTemplate jdbcTemplate;
	
	public UserDao(){
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(DBUtils.getDriver());
		dataSource.setUrl(DBUtils.getUrl());
		dataSource.setUsername(DBUtils.getUserName());
		dataSource.setPassword(DBUtils.getPassword());
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public boolean insert(User user) throws SQLException {
		user.setId(StringUtils.generateUUID());
		String sql = "insert into t_user(id, name, password) values(?, ?, ?)";
		
		int rowChange = 0;
		try{
			rowChange = jdbcTemplate.update(sql, (preStmt) -> {
				preStmt.setString(1, user.getId());
				preStmt.setString(2, user.getName());
				preStmt.setString(3, user.getPassword());
			});
		} catch(EmptyResultDataAccessException e) {
			return false;
		}
		
		if(rowChange != 0){
			return true;
		}
		return false;
	}

	@Override
	public boolean delete(String id) throws SQLException {
		String sql = "delete from t_user t where t.id = ?";
		int rowChange = 0;
		try{
			rowChange = jdbcTemplate.update(sql, (preStmt) -> {
				preStmt.setString(1, id);
			});
		} catch(EmptyResultDataAccessException e) {
			return false;
		}
		
		if(rowChange != 0){
			return true;
		}
		return false;
	}

	@Override
	public boolean update(User user) throws SQLException {
		String sql = "update t_user t set t.name = ? , t.password = ? where t.id = ?";

		int rowChange = 0;
		try{
			rowChange = jdbcTemplate.update(sql, (preStmt) -> {
				preStmt.setString(1, user.getName());
				preStmt.setString(2, user.getPassword());
				preStmt.setString(3, user.getId());
			});
		} catch(EmptyResultDataAccessException e) {
			return false;
		}
		
		if(rowChange != 0){
			return true;
		}
		return false;
	}

	@Override
	public User select(String id) throws SQLException {
		String sql = "select * from t_user t where t.id = ?";
		
		User result = null;
		try{
			result = (User) jdbcTemplate.queryForObject(sql, 
					new Object[]{id}, 
					(rs, rowNum) -> {
						User u = new User();
						u.setId(rs.getString("id"));
						u.setName(rs.getString("name"));
						u.setPassword(rs.getString("password"));
						return u;}
			);
		} catch(EmptyResultDataAccessException e) {
			return null;
		}
		
		return result;
	}

	@Override
	public List<User> select() throws SQLException {
		String sql = "select * from t_user";
		List<User> users = null;
		try{
			users = jdbcTemplate.queryForList(sql, User.class);
		} catch(EmptyResultDataAccessException e) {
			return null;
		}
		return users;
	}

	@Override
	public boolean selectByNameAndPassword(User user) throws SQLException {
		String sql = "select * from t_user t where t.name = ? and t.password = ?";
		User result = null;
		try{
			result = (User) jdbcTemplate.queryForObject(sql, 
					new Object[]{user.getName(), user.getPassword()}, 
					(rs, rowNum) -> {
						User u = new User();
						u.setId(rs.getString("id"));
						u.setName(rs.getString("name"));
						u.setPassword(rs.getString("password"));
						return u;}
			);
		} catch(EmptyResultDataAccessException e) {
			return false;
		}
		
		if(result!=null){
			return true;
		}
		return false;
	}

	@Override
	public boolean selectByName(String name) throws SQLException {
		String sql = "select * from t_user t where t.name = ?";
		User result = null;
		try{
			result = (User) jdbcTemplate.queryForObject(sql, 
					new Object[]{name}, 
					(rs, rowNum) -> {
						User u = new User();
						u.setId(rs.getString("id"));
						u.setName(rs.getString("name"));
						u.setPassword(rs.getString("password"));
						return u;}
			);
		} catch(EmptyResultDataAccessException e) {
			return false;
		}
		
		if(result!=null){
			return true;
		}
		return false;
	}

}
