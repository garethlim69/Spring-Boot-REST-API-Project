package com.gareth.springbootassessment.usersystem.exception;

public class ExceptionResponse {
	private String error;

	public ExceptionResponse(String error) {
		super();
		this.error = error;
	}

	public String getError() {
		error = "Failed";
		return error;
	}

}
