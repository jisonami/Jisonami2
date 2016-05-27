package org.jisonami.jisonami2.service;

import java.util.List;

import org.jisonami.jisonami2.dao.BlogRepository;
import org.jisonami.jisonami2.entity.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlogService {
	
	@Autowired
	private BlogRepository repository;
	
	public boolean save(Blog blog) {
		repository.save(blog);
		return true;
	}
	public boolean delete(String id) {
		repository.delete(id);
		return true;
	}
	public boolean edit(Blog blog) {
		repository.save(blog);
		return true;
	}
	public List<Blog> query() {
		return (List<Blog>) repository.findAll();
	}
	public Blog queryById(String id) {
		return repository.findOne(id);
	}
	public List<Blog> queryByAuthor(String author) {
		return repository.findByAuthor(author);
	}
	public List<Blog> queryByBlogType(String blogTypeId) {
		return repository.findByBlogTypeLike(blogTypeId);
	}
	public int blogCountByBlogType(String blogTypeId) {
		List<Blog> blogs = repository.findByBlogTypeLike("%"+blogTypeId+"%");
		if(blogs!=null){
			return blogs.size();
		} else {
			return 0;
		}
	}
	
}
