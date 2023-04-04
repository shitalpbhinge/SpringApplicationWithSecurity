package com.example.spring.jwt.mongodb;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootSecurityJwtMongodbApplication 
{
	private static final Logger logger = LoggerFactory.getLogger(SpringBootSecurityJwtMongodbApplication.class);
	public static void main(String[] args) 
	{
		SpringApplication.run(SpringBootSecurityJwtMongodbApplication.class, args);
		logger.info("This is a info message");
		  logger.debug("This is a debug message");
		  logger.warn("This is a warn message");
		  Set<Thread> threads = Thread.getAllStackTraces().keySet();
	         
	        for (Thread t : threads) {
	            String name = t.getName();
	            Thread.State state = t.getState();
	            int priority = t.getPriority();
	            String type = t.isDaemon() ? "Daemon" : "Normal";
	            System.out.printf("%-20s \t %s \t %d \t %s\n", name, state, priority, type);
	        }
	}

}
