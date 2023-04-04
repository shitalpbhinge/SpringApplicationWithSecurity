package com.example.spring.jwt.mongodb.models;


import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "notifications")
public class Notification {
    
    @Id
    private String id;
    
    private String to;
    
    private String from;
    
    private Date date;
    
    private List<StatusList> statusList;
    
    // Constructors, getters, and setters
    
    public Notification(String to, String from, Date date, List<StatusList> statusList) {
        this.to = to;
        this.from = from;
        this.date = date;
        this.statusList = statusList;
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<StatusList> getStatusList() {
		return statusList;
	}

	public void setStatusList(List<StatusList> statusList) {
		this.statusList = statusList;
	}

	public Notification() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Notification [id=" + id + ", to=" + to + ", from=" + from + ", date=" + date + ", statusList="
				+ statusList + "]";
	}
    
    
}
