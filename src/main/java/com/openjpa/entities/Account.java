package com.openjpa.entities;

import javax.persistence.*;

@Entity
@Table( name = "ACCOUNT" )
public class Account {
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private Integer id;
	
	@Column( name = "NAME" )
	private String name;
	
	@Override
	public String toString() {
		
		return "Account [id=" + id + ", name=" + name + "]";
		
	}

	public Account(String name) {
		
		this.name = name;
		
	}
	
	public String getName() {
		
		return name;
		
	}
	
	public void setName(String name) {
		
		this.name = name;
		
	}
	
	public long getId() {
		
		return id;
		
	}

	public Account() {
		
		super();
		
	}
	
}