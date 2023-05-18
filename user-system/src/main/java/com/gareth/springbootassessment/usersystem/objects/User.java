package com.gareth.springbootassessment.usersystem.objects;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Entity
@Table(name = "user")
@JsonFilter("Filter")
@JsonPropertyOrder({"username", "dispUsername","userId"})
public class User {
	
	@Id
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@GeneratedValue(generator = "UUID")
	@Column(name = "userID")
	private UUID userId;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "displayusername")
	private String dispUsername;
	
	@Column(name = "password")
	private String password;
	
	
	public User() {
		super();
	}

	public User(UUID userId, String username, String dispUsername, String password) {
		super();
		this.userId = userId;
		this.username = username;
		this.dispUsername = dispUsername;
		this.password = password;
	}

	public UUID getUserId() {
		return userId;
	}

	public void setUserId(UUID userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDispUsername() {
		return dispUsername;
	}

	public void setDispUsername(String dispUsername) {
		this.dispUsername = dispUsername;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Object map(Object object) {
		// TODO Auto-generated method stub
		return null;
	}	
}
