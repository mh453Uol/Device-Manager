package com.mh453Uol.domain;

import java.util.UUID;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class User {
	private UUID Id; 
	
	@NotEmpty
	@Email
    @Length(min = 2, message = "The field must be at least 2 characters")
	private String email;
	//using basic auth password is encoded in base64
	
	@NotEmpty
    @Length(min = 5, message = "The field must be at least 5 characters")
	private String password;
	
	public User() {
		this.Id = UUID.randomUUID();
	}
	
	public User(String email,String password) {
		super();
		this.email = email;
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public UUID getId() {
		return this.Id;
	}
}
