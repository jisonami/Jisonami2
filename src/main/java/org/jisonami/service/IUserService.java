package org.jisonami.service;

import org.jisonami.entity.User;

public interface IUserService {
	
	public boolean validate(User user) throws Exception;
	
	public boolean save(User user) throws Exception;
	
	public boolean delete(String id) throws Exception;
	
	public boolean edit(User user) throws Exception;
	
	public boolean exits(User user) throws Exception;
	
}
