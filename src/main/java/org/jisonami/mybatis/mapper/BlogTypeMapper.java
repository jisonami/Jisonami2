package org.jisonami.mybatis.mapper;

import java.util.List;

import org.jisonami.entity.BlogType;

public interface BlogTypeMapper {
	
	public void save(BlogType blogType);
	
	public void delete(String blogTypeId);
	
	public void edit(BlogType blogType);
	
	public List<BlogType> queryByAuthor(String blogAuthor);
	
	public BlogType queryById(String blogTypeId);
	
}
