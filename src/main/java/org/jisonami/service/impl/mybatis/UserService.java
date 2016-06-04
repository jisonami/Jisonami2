//package org.jisonami.service.impl.mybatis;
//
//import org.jisonami.dao.impl.mybatis.UserMapper;
//import org.jisonami.entity.User;
//import org.jisonami.service.IUserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.util.StringUtils;
//
//@Service
//public class UserService implements IUserService{
//	
//	@Autowired
//	private UserMapper userMapper;
//	
//	public boolean validate(User user){
//		String id = userMapper.validate(user);
//		if(!StringUtils.isEmpty(id)){
//			return true;
//		}
//		return false;
//	}
//	public boolean save(User user) {
//		userMapper.save(user);
//		return true;
//	}
//	
//	public boolean delete(String id) {
//		userMapper.delete(id);
//		return true;
//	}
//	public boolean edit(User user) {
//		userMapper.edit(user);
//		return true;
//	}
//	public boolean exits(User user) {
//		String id = userMapper.exits(user.getName());
//		if(!StringUtils.isEmpty(id)){
//			return true;
//		}
//		return false;
//	}
//}
