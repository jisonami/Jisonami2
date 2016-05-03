package org.jisonami.service;

import java.util.List;

import org.jisonami.entity.Blog;
import org.jisonami.mybatis.mapper.BlogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlogService {
	
	@Autowired
	private BlogMapper blogMapper;
	
	public boolean save(Blog blog) {
		blogMapper.save(blog);
		return true;
	}
	public boolean delete(String id) {
		blogMapper.delete(id);
		return true;
	}
	public boolean edit(Blog blog) {
		blogMapper.edit(blog);
		return true;
	}
	public List<Blog> query() {
		return blogMapper.query();
	}
	public Blog queryById(String id) {
		return blogMapper.queryById(id);
	}
	public List<Blog> queryByAuthor(String author) {
		return blogMapper.queryByAuthor(author);
	}
	public List<Blog> queryByBlogType(String blogTypeId) {
		return blogMapper.queryByBlogType(blogTypeId);
	}
	public int blogCountByBlogType(String blogTypeId) {
		return blogMapper.blogCountByBlogType(blogTypeId);
	}
	
}
