package org.jisonami.dao.impl.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.jisonami.dao.IUserDao;
import org.jisonami.entity.User;
import org.jisonami.util.DBUtils;
import org.jisonami.util.StringUtils;

public class UserDao implements IUserDao {

	@Override
	public boolean insert(User user) throws SQLException{
		user.setId(StringUtils.generateUUID());
		Connection conn = DBUtils.getConnection();
		String sql = "insert into t_user(id, name, password) values(?, ?, ?)";
		PreparedStatement preStmt = conn.prepareStatement(sql);
		preStmt.setString(1, user.getId());
		preStmt.setString(2, user.getName());
		preStmt.setString(3, user.getPassword());
		int rowChange = preStmt.executeUpdate();
		preStmt.close();
		conn.close();
		if(rowChange != 0){
			return true;
		}
		return false;
	}

	@Override
	public boolean update(User user) throws SQLException{
		Connection conn = DBUtils.getConnection();
		String sql = "update t_user t set t.name = ? , t.password = ? where t.id = ?";
		PreparedStatement preStmt = conn.prepareStatement(sql);
		preStmt.setString(1, user.getName());
		preStmt.setString(2, user.getPassword());
		preStmt.setString(3, user.getId());
		int rowChanges = preStmt.executeUpdate();
		preStmt.close();
		conn.close();
		if(rowChanges != 0){
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean delete(String id) throws SQLException{
		Connection conn = DBUtils.getConnection();
		String sql = "delete from t_user t where t.id = ?";
		PreparedStatement preStmt = conn.prepareStatement(sql);
		preStmt.setString(1, id);
		int rowChanges = preStmt.executeUpdate();
		preStmt.close();
		conn.close();
		if(rowChanges != 0){
			return true;
		} else {
			return false;
		}
	}

	@Override
	public User select(String id) throws SQLException{
		Connection conn = DBUtils.getConnection();
		String sql = "select * from t_user t where t.id = ?";
		PreparedStatement preStmt = conn.prepareStatement(sql);
		preStmt.setString(1, id);
		ResultSet rs = preStmt.executeQuery();
		
		// 根据id查应该是只有一行数据的
		User user = new User();
		if(rs.next()){
			user.setId(rs.getString("id"));
			user.setName(rs.getString("name"));
			user.setPassword(rs.getString("password"));
		}
		
		rs.close();
		preStmt.close();
		conn.close();
		return user;
	}

	@Override
	public List<User> select() throws SQLException{
		Connection conn = DBUtils.getConnection();
		String sql = "select * from t_user";
		PreparedStatement preStmt = conn.prepareStatement(sql);
		ResultSet rs = preStmt.executeQuery();
		
		List<User> users = new ArrayList<User>();
		while(rs.next()){
			User user = new User();
			user.setId(rs.getString("id"));
			user.setName(rs.getString("name"));
			user.setPassword(rs.getString("password"));
			users.add(user);
		}
		
		rs.close();
		preStmt.close();
		conn.close();
		return users;
	}

	@Override
	public boolean selectByNameAndPassword(User user) throws SQLException {
		Connection conn = DBUtils.getConnection();
		String sql = "select * from t_user t where t.name = ? and t.password = ?";
		PreparedStatement preStmt = conn.prepareStatement(sql);
		preStmt.setString(1, user.getName());
		preStmt.setString(2, user.getPassword());
		ResultSet rs = preStmt.executeQuery();
		boolean hasNextRow = rs.next();
		rs.close();
		preStmt.close();
		conn.close();
		if(hasNextRow){
			return true;
		}
		return false;
	}

	@Override
	public boolean selectByName(String name) throws SQLException {
		Connection conn = DBUtils.getConnection();
		String sql = "select * from t_user t where t.name = ?";
		PreparedStatement preStmt = conn.prepareStatement(sql);
		preStmt.setString(1, name);
		ResultSet rs = preStmt.executeQuery();
		boolean hasNextRow = rs.next();
		rs.close();
		preStmt.close();
		conn.close();
		if(hasNextRow){
			return true;
		}
		return false;
	}

}
