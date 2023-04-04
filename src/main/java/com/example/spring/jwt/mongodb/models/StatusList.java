package com.example.spring.jwt.mongodb.models;
import org.springframework.stereotype.Component;

@Component
public class StatusList 
{
	private String status;
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public StatusList(String status) {
		super();
		this.status = status;
	}

	public StatusList() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "StatusList [status=" + status + "]";
	}
	
}
