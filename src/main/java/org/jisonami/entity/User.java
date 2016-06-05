package org.jisonami.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="t_user")
public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	public User(){}
	@Id
	@GeneratedValue(generator="usergenerator")
	@GenericGenerator(name="usergenerator", strategy="uuid")
	private String id;
	private String name;
	private String password;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
