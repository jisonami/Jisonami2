package org.jisonami.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.jisonami.entity.BlogType;
import org.jisonami.hibernate.DataRepository;
import org.jisonami.mybatis.MyBatisSessionFactoryBuilder;
import org.jisonami.mybatis.mapper.BlogTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlogTypeService {
	
	@Autowired
	DataRepository repository;
	
	public boolean save(BlogType blogType) {
		SqlSessionFactory sqlSessionFactory = MyBatisSessionFactoryBuilder.buildSqlSessionFactory();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		BlogTypeMapper blogTypeMapper = sqlSession.getMapper(BlogTypeMapper.class);
		blogTypeMapper.save(blogType);
		sqlSession.commit();
		sqlSession.close();
		return true;
	}
	public boolean delete(String blogTypeId) {
		SqlSessionFactory sqlSessionFactory = MyBatisSessionFactoryBuilder.buildSqlSessionFactory();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		BlogTypeMapper blogTypeMapper = sqlSession.getMapper(BlogTypeMapper.class);
		blogTypeMapper.delete(blogTypeId);
		sqlSession.commit();
		sqlSession.close();
		return true;
	}
	public boolean edit(BlogType blogType) {
		SqlSessionFactory sqlSessionFactory = MyBatisSessionFactoryBuilder.buildSqlSessionFactory();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		BlogTypeMapper blogTypeMapper = sqlSession.getMapper(BlogTypeMapper.class);
		blogTypeMapper.edit(blogType);
		sqlSession.commit();
		sqlSession.close();
		return true;
	}
	public List<BlogType> queryByAuthor(String blogAuthor) {
		SqlSessionFactory sqlSessionFactory = MyBatisSessionFactoryBuilder.buildSqlSessionFactory();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		BlogTypeMapper blogTypeMapper = sqlSession.getMapper(BlogTypeMapper.class);
		List<BlogType> blogTypes = blogTypeMapper.queryByAuthor(blogAuthor);
		sqlSession.close();
		return blogTypes;
	}
	public BlogType queryById(String blogTypeId) {
		SqlSessionFactory sqlSessionFactory = MyBatisSessionFactoryBuilder.buildSqlSessionFactory();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		BlogTypeMapper blogTypeMapper = sqlSession.getMapper(BlogTypeMapper.class);
		BlogType blogType = blogTypeMapper.queryById(blogTypeId);
		sqlSession.close();
		return blogType;
	}
}
