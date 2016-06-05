package org.jisonami.dao.impl.jdbctemplate;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbcp.BasicDataSource;
import org.jisonami.dao.IUserDao;
import org.jisonami.entity.User;
import org.jisonami.util.DBUtils;
import org.jisonami.util.StringUtils;
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
		
		int rowChange = jdbcTemplate.update(sql, (preStmt) -> {
			preStmt.setString(1, user.getId());
			preStmt.setString(2, user.getName());
			preStmt.setString(3, user.getPassword());
		});
		
		if(rowChange != 0){
			return true;
		}
		return false;
	}

	@Override
	public boolean delete(String id) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(User user) throws SQLException {
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
		String sql = "select name,password from t_user t where t.name = ? and t.password = ?";
		User result = (User) jdbcTemplate.queryForObject(sql, new Object[]{
				user.getName(),
				user.getPassword()
				}, 
				(rs, rowNum) -> {
					User u = new User();
					u.setId(rs.getString("id"));
					u.setName(rs.getString("name"));
					u.setPassword(rs.getString("password"));
					return u;}
		);
		if(result!=null){
			return true;
		}
		return false;
	}

	@Override
	public boolean selectByName(String name) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

}
