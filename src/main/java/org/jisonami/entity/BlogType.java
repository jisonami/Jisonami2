package org.jisonami.entity;

import java.io.Serializable;

public class BlogType implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public BlogType(){}
	private String id;
	private String blogAuthor;
	private String name;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBlogAuthor() {
		return blogAuthor;
	}
	public void setBlogAuthor(String blogAuthor) {
		this.blogAuthor = blogAuthor;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
