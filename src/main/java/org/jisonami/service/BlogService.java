package org.jisonami.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.jisonami.entity.Blog;
import org.jisonami.hibernate.DataRepository;
import org.jisonami.mybatis.MyBatisSessionFactoryBuilder;
import org.jisonami.mybatis.mapper.BlogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlogService {
	
	@Autowired
	DataRepository repository;
	
	public boolean save(Blog blog) {
		SqlSessionFactory sqlSessionFactory = MyBatisSessionFactoryBuilder.buildSqlSessionFactory();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);
		blogMapper.save(blog);
		sqlSession.commit();
		sqlSession.close();
		return true;
	}
	public boolean delete(String id) {
		SqlSessionFactory sqlSessionFactory = MyBatisSessionFactoryBuilder.buildSqlSessionFactory();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);
		blogMapper.delete(id);
		sqlSession.commit();
		sqlSession.close();
		return true;
	}
	public boolean edit(Blog blog) {
		SqlSessionFactory sqlSessionFactory = MyBatisSessionFactoryBuilder.buildSqlSessionFactory();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);
		blogMapper.edit(blog);
		sqlSession.commit();
		sqlSession.close();
		return true;
	}
	public List<Blog> query() {
		SqlSessionFactory sqlSessionFactory = MyBatisSessionFactoryBuilder.buildSqlSessionFactory();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);
		List<Blog> blogs = blogMapper.query();
		sqlSession.close();
		return blogs;
	}
	public Blog queryById(String id) {
		SqlSessionFactory sqlSessionFactory = MyBatisSessionFactoryBuilder.buildSqlSessionFactory();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);
		Blog blog = blogMapper.queryById(id);
		sqlSession.close();
		return blog;
	}
	public List<Blog> queryByAuthor(String author) {
		SqlSessionFactory sqlSessionFactory = MyBatisSessionFactoryBuilder.buildSqlSessionFactory();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);
		List<Blog> blogs = blogMapper.queryByAuthor(author);
		sqlSession.close();
		return blogs;
	}
	public List<Blog> queryByBlogType(String blogTypeId) {
		SqlSessionFactory sqlSessionFactory = MyBatisSessionFactoryBuilder.buildSqlSessionFactory();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);
		List<Blog> blogs = blogMapper.queryByBlogType(blogTypeId);
		sqlSession.close();
		return blogs;
	}
	public int blogCountByBlogType(String blogTypeId) {
		SqlSessionFactory sqlSessionFactory = MyBatisSessionFactoryBuilder.buildSqlSessionFactory();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);
		int blogCount = blogMapper.blogCountByBlogType(blogTypeId);
		sqlSession.close();
		return blogCount;
	}
	
}
