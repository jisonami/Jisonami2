package org.jisonami.dao;

import java.sql.SQLException;

import org.jisonami.entity.User;

public interface IUserDao extends Repository<User>{

	public boolean selectByNameAndPassword(User user) throws SQLException;
	
	public boolean selectByName(String name) throws SQLException;
}
