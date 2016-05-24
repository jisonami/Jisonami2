package org.jisonami.jisonami2.dao;

import java.util.List;

import org.jisonami.jisonami2.entity.BlogType;
import org.springframework.data.repository.CrudRepository;

public interface BlogTypeRepository extends CrudRepository<BlogType, String>{

	public List<BlogType> findByBlogAuthor(String blogAuthor);
	
}
