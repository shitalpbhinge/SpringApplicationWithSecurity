package com.example.spring.jwt.mongodb.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring.jwt.mongodb.entity.EmailRequest;
import com.example.spring.jwt.mongodb.service.EmailService;


@CrossOrigin("http://localhost:8080")
@RestController
@RequestMapping("apiMail")
	public class EmailController 
	{
		private static final Logger logger = LoggerFactory.getLogger(EmailController .class);
	    @Autowired
	    private EmailService emailService;

	    //this api send simple email
	    @PostMapping("/sendingemail")
	    public ResponseEntity<?> sendEmail(@RequestBody EmailRequest request)
	    {
	    	logger.info("mail send Successfully.");
	    	
	        System.out.println(request);
	        

	        boolean result = this.emailService.sendEmail(request.getSubject(), request.getMessage(), request.getTo());

	        if(result){
	        	
	           return  ResponseEntity.ok("Email Properly Sent Successfully... ");

	        }else{

	            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("email sending fail");
	        }
	    }
}
