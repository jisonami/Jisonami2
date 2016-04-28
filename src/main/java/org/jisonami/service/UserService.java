package org.jisonami.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jisonami.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class UserService {
	
	@Autowired
	DataRepository repository;
	
	public boolean validate(User user) {
		String hql = "from User as u where u.name = :name and u.password = :password";
		Map<String, Object> queryParam = new HashMap<String, Object>();
		queryParam.put("name", user.getName());
		queryParam.put("password", user.getPassword());
		List<User> users = repository.query(hql, queryParam);
		if(users!=null && users.size()>0){
			return true;
		}
		return false;
	}
	public boolean save(User user) {
		String id = repository.add(user);
		if(StringUtils.isEmpty(id)){
			return false;
		}
		return true;
	}
	public boolean delete(String id) {
		return repository.delete(User.class, id);
	}
	public boolean edit(User user) {
		return repository.edit(user);
	}
	public boolean exits(User user) {
		if(query(user.getName())){
			return true;
		}
		return false;
	}
	public boolean query(String username) {
		String hql = "from User as u where u.name = :name";
		Map<String, Object> queryParam = new HashMap<String, Object>();
		queryParam.put("name", username);
		List<User> users = repository.query(hql, queryParam);
		if(users!=null && users.size()>0){
			return true;
		}
		return false;
	}
}
