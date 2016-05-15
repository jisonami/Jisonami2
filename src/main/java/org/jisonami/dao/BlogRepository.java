package org.jisonami.dao;

import java.util.List;

import org.jisonami.entity.Blog;
import org.springframework.data.repository.CrudRepository;

public interface BlogRepository extends CrudRepository<Blog, String>{
	
	public List<Blog> findByAuthor(String author);
	
	public List<Blog> findByBlogTypeLike(String blogType);
	
}
