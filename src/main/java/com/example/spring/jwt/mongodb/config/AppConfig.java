package com.example.spring.jwt.mongodb.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.spring.jwt.mongodb.factory.ScriptExecutionFactory;



@Configuration
public class AppConfig 
{

	@Autowired
	ScriptExecutionFactory scriptexec;
    /*@Bean
    public String scriptPath() {
        return "/path/to/python/script.py"; // replace with actual path to your Python script
    }*/
	@Bean
    public String myStringBean() {
        return "myStringBeanValue";
    }
    // other configuration beans and methods...
  /*  @Bean
    public Logger getLogger() {
        return LoggerFactory.getLogger(PythonScriptExecutor.class);
    }*/
}
