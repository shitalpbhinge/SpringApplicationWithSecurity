package com.example.spring.jwt.mongodb.payload.response;

public class MessageResponce {
	private String message;

	public MessageResponce(String message) {
	    this.message = message;
	  }

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
