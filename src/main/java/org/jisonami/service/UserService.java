package org.jisonami.service;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.jisonami.entity.User;
import org.jisonami.hibernate.DataRepository;
import org.jisonami.mybatis.MyBatisSessionFactoryBuilder;
import org.jisonami.mybatis.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class UserService {
	
	@Autowired
	DataRepository repository;
	
	public boolean validate(User user){
		SqlSessionFactory sqlSessionFactory = MyBatisSessionFactoryBuilder.buildSqlSessionFactory();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		String id = userMapper.validate(user);
		sqlSession.close();
		if(!StringUtils.isEmpty(id)){
			return true;
		}
		return false;
	}
	public boolean save(User user) {
		SqlSessionFactory sqlSessionFactory = MyBatisSessionFactoryBuilder.buildSqlSessionFactory();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		userMapper.save(user);
		sqlSession.commit();
		sqlSession.close();
		return true;
	}
	public boolean delete(String id) {
		SqlSessionFactory sqlSessionFactory = MyBatisSessionFactoryBuilder.buildSqlSessionFactory();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		userMapper.delete(id);
		sqlSession.commit();
		sqlSession.close();
		return true;
	}
	public boolean edit(User user) {
		SqlSessionFactory sqlSessionFactory = MyBatisSessionFactoryBuilder.buildSqlSessionFactory();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		userMapper.edit(user);
		sqlSession.commit();
		sqlSession.close();
		return true;
	}
	public boolean exits(User user) {
		SqlSessionFactory sqlSessionFactory = MyBatisSessionFactoryBuilder.buildSqlSessionFactory();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		String id = userMapper.exits(user.getName());
		sqlSession.close();
		if(!StringUtils.isEmpty(id)){
			return true;
		}
		return false;
	}
}
