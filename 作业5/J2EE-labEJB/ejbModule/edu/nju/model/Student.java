package edu.nju.model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="students")
public class Student implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private int id;
	
	private String name;
	
	private String password;

	@Id
	public int getId() {
		return id;
	}

	public void setId(int id) {
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
