package com.example.spring.jwt.mongodb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "usersdemo")

public class UserDemo 
{
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	
	    @Column(nullable = false)
	    private String name;
	
	    @Column(nullable = false)
	    private String email;
	
		public Long getId() {
			return id;
		}
	
		public void setId(Long id) {
			this.id = id;
		}
	
		public String getName() {
			return name;
		}
	
		public void setName(String name) {
			this.name = name;
		}
	
		public String getEmail() {
			return email;
		}
	
		public void setEmail(String email) {
			this.email = email;
		}
	
		public UserDemo(Long id, String name, String email) {
			super();
			this.id = id;
			this.name = name;
			this.email = email;
		}
	
		public UserDemo() {
			super();
			// TODO Auto-generated constructor stub
		}
	
	    
}
