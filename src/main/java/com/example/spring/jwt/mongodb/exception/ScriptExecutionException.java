package com.example.spring.jwt.mongodb.exception;


import org.springframework.http.HttpStatus;

public class ScriptExecutionException extends RuntimeException 
{

    private final HttpStatus status;

    public ScriptExecutionException(String message) 
    {
        super(message);
        HttpStatus exception = null;
		this.status = exception;
    }

    public ScriptExecutionException(String message, Throwable cause, HttpStatus status)
    {
        this.status = null;
		
    }

	
}