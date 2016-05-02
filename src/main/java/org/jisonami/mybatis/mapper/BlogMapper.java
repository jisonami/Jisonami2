package org.jisonami.mybatis.mapper;

import java.util.List;

import org.jisonami.entity.Blog;

public interface BlogMapper {
	
	public void save(Blog blog);
	
	public void delete(String id);
	
	public void edit(Blog blog);
	
	public List<Blog> query();
	
	public Blog queryById(String id);
	
	public List<Blog> queryByAuthor(String author);
	
	public List<Blog> queryByBlogType(String blogTypeId);
	
	public int blogCountByBlogType(String blogTypeId);
	
}
