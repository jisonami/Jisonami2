package org.jisonami.service;

import java.util.List;

import org.jisonami.entity.BlogType;
import org.jisonami.mybatis.mapper.BlogTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlogTypeService {
	
	@Autowired
	private BlogTypeMapper blogTypeMapper;
	
	public boolean save(BlogType blogType) {
		blogTypeMapper.save(blogType);
		return true;
	}
	public boolean delete(String blogTypeId) {
		blogTypeMapper.delete(blogTypeId);
		return true;
	}
	public boolean edit(BlogType blogType) {
		blogTypeMapper.edit(blogType);
		return true;
	}
	public List<BlogType> queryByAuthor(String blogAuthor) {
		return blogTypeMapper.queryByAuthor(blogAuthor);
	}
	public BlogType queryById(String blogTypeId) {
		return blogTypeMapper.queryById(blogTypeId);
	}
}
