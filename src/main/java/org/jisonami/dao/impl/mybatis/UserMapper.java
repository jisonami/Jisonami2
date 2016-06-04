package org.jisonami.dao.impl.mybatis;

import org.jisonami.entity.User;

public interface UserMapper {
	
	public void save(User user);
	
	public void delete(String id);
	
	public void edit(User user);
	
	public String validate(User user);
	
	public String exits(String username);
	
}
