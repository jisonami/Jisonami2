package org.jisonami.service;

import java.util.List;

import org.jisonami.dao.BlogTypeRepository;
import org.jisonami.entity.BlogType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlogTypeService {
	
	@Autowired
	private BlogTypeRepository repository;
	
	public boolean save(BlogType blogType) {
		repository.save(blogType);
		return true;
	}
	public boolean delete(String blogTypeId) {
		repository.delete(blogTypeId);
		return true;
	}
	public boolean edit(BlogType blogType) {
		repository.save(blogType);
		return true;
	}
	public List<BlogType> queryByAuthor(String blogAuthor) {
		return repository.findByBlogAuthor(blogAuthor);
	}
	public BlogType queryById(String blogTypeId) {
		return repository.findOne(blogTypeId);
	}
}
