package org.jisonami.dao.impl.mybatis;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.jisonami.dao.IUserDao;
import org.jisonami.dao.impl.mybatis.mapper.UserMapper;
import org.jisonami.entity.User;
import org.springframework.util.StringUtils;

public class UserDao implements IUserDao {

	private UserMapper userMapper;
	
	public UserDao(){
		InputStream inputStream = null;
		try {
			inputStream = Resources.getResourceAsStream("mybatis.xml");
		} catch (IOException e) {
			e.printStackTrace();
		}
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession sqlSession = sqlSessionFactory.openSession();
		userMapper = sqlSession.getMapper(UserMapper.class);
	}
	
	@Override
	public boolean insert(User user) throws SQLException {
		userMapper.save(user);
		return true;
	}

	@Override
	public boolean delete(String id) throws SQLException {
		userMapper.delete(id);
		return true;
	}

	@Override
	public boolean update(User user) throws SQLException {
		userMapper.edit(user);
		return true;
	}

	@Override
	public User select(String id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> select() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean selectByNameAndPassword(User user) throws SQLException {
		String id = userMapper.validate(user);
		if(!StringUtils.isEmpty(id)){
			return true;
		}
		return false;
	}

	@Override
	public boolean selectByName(String name) throws SQLException {
		String id = userMapper.exits(name);
		if(!StringUtils.isEmpty(id)){
			return true;
		}
		return false;
	}

}
