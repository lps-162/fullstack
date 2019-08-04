package com.euler.topguns.entities;

import java.util.HashMap;
import java.util.Map;

public class Customer {

	private int id;
	private String firstName;
	private String lastName;
	private String email;
	
	// one can adopt Lombok library for projects
	public Customer() {
		
	}
	
	public Customer(int id, String firstName, String lastName, String email) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Map<String, Object> toMap() {
		  Map<String, Object> values = new HashMap<>();
		  values.put("first_name", firstName);
		  values.put("last_name", lastName);
		  values.put("email", email);
		  return values;
		}
}