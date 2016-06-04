package org.jisonami.dao.impl.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
	public User selectOne(String id) throws SQLException{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> select() throws SQLException{
		// TODO Auto-generated method stub
		return null;
	}

}
