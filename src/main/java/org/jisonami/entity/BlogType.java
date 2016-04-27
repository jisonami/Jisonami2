package org.jisonami.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="t_blogtype")
public class BlogType implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public BlogType(){}
	@Id
	@GeneratedValue(generator="blogtypegenerator")
	@GenericGenerator(name="blogtypegenerator",strategy="uuid")
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
