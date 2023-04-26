package com.example.spring.jwt.mongodb.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.spring.jwt.mongodb.interfaces.Database;
import com.example.spring.jwt.mongodb.service.MongoDatabase;
import com.example.spring.jwt.mongodb.service.SqlDatabase;
@Component
public class DatabaseType 
{
	@Autowired
	private MongoDatabase mongocrud;
	
	@Autowired
	private SqlDatabase sqlcrud;
	
	    public Database getBookRepository(String type) 
	    {
	        if ("mysql".equalsIgnoreCase(type)) 
	        {
	            return sqlcrud;
	        } 
	        else if ("mongodb".equalsIgnoreCase(type)) 
	        {
	            return mongocrud;
	        }
	        throw new IllegalArgumentException("Invalid repository type: " + type);
	    }
}
