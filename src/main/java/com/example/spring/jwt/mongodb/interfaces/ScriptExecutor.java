package com.example.spring.jwt.mongodb.interfaces;

import com.example.spring.jwt.mongodb.exception.ScriptExecutionException;

public interface ScriptExecutor 
{
    String execute(String scriptPath) throws ScriptExecutionException;
}
