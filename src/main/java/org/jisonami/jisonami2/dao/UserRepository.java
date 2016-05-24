package org.jisonami.jisonami2.dao;

import org.jisonami.jisonami2.entity.User;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User, String>{
	
	public User findByName(String name);
	
	public User findByNameAndPassword(String name, String password);
	
}
