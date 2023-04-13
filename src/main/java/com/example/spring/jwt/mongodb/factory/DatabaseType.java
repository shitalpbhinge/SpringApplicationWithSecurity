package com.example.spring.jwt.mongodb.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.BookDesignFactoryMethod.database.Database;
import com.example.BookDesignFactoryMethod.service.MongoDatabase;
import com.example.BookDesignFactoryMethod.service.SqlDatabase;

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
