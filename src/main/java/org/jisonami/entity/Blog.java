package org.jisonami.entity;

import java.io.Serializable;
import java.util.Date;

public class Blog implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Blog(){}
	private String id;
	private String title;
	private String content;
	private String blogType;
	private String author;
	private Date publishTime;
	private Date editTime;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getBlogType() {
		return blogType;
	}
	public void setBlogType(String blogType) {
		this.blogType = blogType;
	}
	public Date getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}
	public Date getEditTime() {
		return editTime;
	}
	public void setEditTime(Date editTime) {
		this.editTime = editTime;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	
}
