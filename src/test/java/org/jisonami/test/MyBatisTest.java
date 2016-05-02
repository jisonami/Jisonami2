package org.jisonami.test;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.jisonami.entity.User;
import org.junit.Test;

public class MyBatisTest {

	@Test
	public void queryone() throws IOException{
		InputStream is = Resources.getResourceAsStream("mybatisconfig/SqlMapConfig.xml");
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
		SqlSession session = sessionFactory.openSession();
		User user = session.selectOne("test.findUserById", "66d3ab5c3f954b6698bcb8627933f237");
		System.out.println(user);
		session.close();
	}
}
