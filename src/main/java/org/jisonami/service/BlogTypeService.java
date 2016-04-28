package org.jisonami.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jisonami.entity.BlogType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class BlogTypeService {
	
	@Autowired
	DataRepository repository;
	
	public boolean save(BlogType blogType) {
		String id = repository.add(blogType);
		if(StringUtils.isEmpty(id)){
			return false;
		}
		return true;
	}
	public boolean delete(String blogTypeId) {
		return repository.delete(BlogType.class, blogTypeId);
	}
	public boolean edit(BlogType blogType) {
		return repository.edit(blogType);
	}
	public List<BlogType> queryByAuthor(String blogAuthor) {
		String hql = "from BlogType bt where bt.blogAuthor = :blogAuthor";
		Map<String, Object> queryParam = new HashMap<String, Object>();
		queryParam.put("blogAuthor", blogAuthor);
		return repository.query(hql, queryParam);
	}
	public BlogType queryById(String blogTypeId) {
		return repository.queryOne(BlogType.class, blogTypeId);
	}
}
