package org.jisonami.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jisonami.entity.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class BlogService {
	
	@Autowired
	DataRepository repository;
	
	public boolean save(Blog blog) {
		String id = repository.add(blog);
		if(StringUtils.isEmpty(id)){
			return false;
		}
		return true;
	}
	public boolean delete(String id) {
		return repository.delete(Blog.class, id);
	}
	public boolean edit(Blog blog) {
		return repository.edit(blog);
	}
	public List<Blog> query() {
		String hql = "from Blog";
		return repository.query(hql);
	}
	public Blog queryById(String id) {
		return repository.queryOne(Blog.class, id);
	}
	public List<Blog> queryByAuthor(String author) {
		String hql = "from Blog as b where b.author = :author";
		Map<String, Object> queryParam = new HashMap<String, Object>();
		queryParam.put("author", author);
		return repository.query(hql, queryParam);
	}
	public List<Blog> queryByBlogType(String blogTypeId) {
		// TODO 注意检查hql的模糊查询
		String hql = "from Blog as b where b.blogType like :blogType";
		Map<String, Object> queryParam = new HashMap<String, Object>();
		queryParam.put("blogType", "%"+blogTypeId+"%");
		return repository.query(hql, queryParam);
	}
	public int blogCountByBlogType(String blogTypeId) {
		String hql = "from Blog as b where b.blogType like :blogType";
		Map<String, Object> queryParam = new HashMap<String, Object>();
		queryParam.put("blogType", "%"+blogTypeId+"%");
		List<Blog> blogs = repository.query(hql, queryParam);
		if(blogs == null){
			return 0;
		}
		return blogs.size();
	}
	
}
