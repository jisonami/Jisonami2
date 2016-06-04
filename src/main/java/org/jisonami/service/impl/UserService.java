package org.jisonami.service.impl;

import java.util.Properties;

import org.jisonami.dao.IUserDao;
import org.jisonami.entity.User;
import org.jisonami.service.IUserService;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService{

	private IUserDao userDao;
	
	public UserService(){
		Properties properties = new Properties();
		try {
			properties.load(this.getClass().getClassLoader().getResourceAsStream("daoConfig.properties"));
			userDao = (IUserDao) Class.forName(properties.getProperty("userDao")).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean validate(User user) throws Exception {
		return userDao.selectByNameAndPassword(user);
	}

	@Override
	public boolean save(User user) throws Exception {
		return userDao.insert(user);
	}

	@Override
	public boolean delete(String id) throws Exception {
		return userDao.delete(id);
	}

	@Override
	public boolean edit(User user) throws Exception {
		return userDao.update(user);
	}

	@Override
	public boolean exits(User user) throws Exception {
		if(userDao.selectByName(user.getName())){
			return true;
		}
		return false;
	}

}
