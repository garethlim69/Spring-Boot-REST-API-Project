package com.gareth.springbootassessment.usersystem.authentication;

import java.util.UUID;

public class AuthenticationRepsonse {
	
	private final String token;
	
	private final UUID userId;
	
	private final String displayusername;
	

	public AuthenticationRepsonse(String token, String displayusername, UUID userid) {
		super();
		this.token = token;
		this.displayusername = displayusername;
		this.userId = userid;
	}

	public String getToken() {
		return token;
	}
	
	public String getDispUsername() {
		return displayusername;
	}
	
	public UUID getUserId() {
		return userId;
	}
}