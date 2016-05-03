package org.jisonami.test;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.jisonami.entity.User;
import org.jisonami.mybatis.mapper.UserMapper;
import org.junit.Test;

public class MyBatisTest {

	@Test
	public void queryone(User user) throws IOException{
		InputStream is = Resources.getResourceAsStream("SqlMapConfig.xml");
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
		SqlSession session = sessionFactory.openSession();
		UserMapper userMapper = session.getMapper(UserMapper.class);
		userMapper.save(user);
		session.commit();
		session.close();
	}
}
