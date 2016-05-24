package org.jisonami.jisonami2.service;

import org.jisonami.jisonami2.dao.UserRepository;
import org.jisonami.jisonami2.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	public boolean validate(User user){
		User entity = repository.findByNameAndPassword(user.getName(), user.getPassword());
		if(entity!=null){
			return true;
		}
		return false;
	}
	public boolean save(User user) {
		repository.save(user);
		return true;
	}
	
	public boolean delete(String id) {
		repository.delete(id);
		return true;
	}
	public boolean edit(User user) {
		repository.save(user);
		return true;
	}
	public boolean exits(User user) {
		User entity = repository.findByName(user.getName());
		if(entity!=null){
			return true;
		}
		return false;
	}
}
