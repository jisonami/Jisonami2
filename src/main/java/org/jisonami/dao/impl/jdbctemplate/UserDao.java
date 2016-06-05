package org.jisonami.dao.impl.jdbctemplate;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbcp.BasicDataSource;
import org.jisonami.dao.IUserDao;
import org.jisonami.entity.User;
import org.jisonami.util.DBUtils;
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
	public boolean insert(User t) throws SQLException {
//		jdbcTemplate.update(sql, pss)
		return false;
	}

	@Override
	public boolean delete(String id) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(User t) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User select(String id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> select() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean selectByNameAndPassword(User user) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean selectByName(String name) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

}
