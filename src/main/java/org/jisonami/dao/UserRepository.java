package org.jisonami.dao;

import org.jisonami.entity.User;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User, String>{
	
	public User findByName(String name);
	
	public User findByNameAndPassword(String name, String password);
	
}
