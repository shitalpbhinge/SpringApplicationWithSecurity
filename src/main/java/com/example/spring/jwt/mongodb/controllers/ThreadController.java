package com.example.spring.jwt.mongodb.controllers;

import java.util.List;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import com.example.spring.jwt.mongodb.models.Notification;
import com.example.spring.jwt.mongodb.service.ThreadService;

import org.springframework.data.mongodb.core.query.Query;

@RestController
@RequestMapping("/mail")
public class ThreadController 
{
		private static final Logger logger = LoggerFactory.getLogger(ThreadController.class);
		@Autowired
		private JavaMailSender javaMailSender;
		
		@Autowired
		MongoTemplate mongoTemplate;
	    @Autowired
	    private ThreadService mailService;

	    @PostMapping("/send")
	    public ResponseEntity<String> sendMail(@RequestParam String email, @RequestParam String subject, @RequestParam String body) throws Exception
	    {
	        mailService.sendMail(email, subject, body);
	        logger.info("Sending email to " + email + " with subject: " + subject);
	        return ResponseEntity.ok("Mail sent successfully");
	    }
	    @PostMapping("/send-mail")
	    public CompletableFuture<List<Notification>> sendMail(@RequestParam("to") List<String> toList,
	                                                           @RequestParam("subject") String subject,
	                                                           @RequestParam("body") String body) {
	        Callable<List<Notification>> callable = mailService.sendMailAsync(toList, subject, body);
	        return CompletableFuture.supplyAsync(() -> {
	            try {
	                return callable.call();
	            } catch (Exception e) {
	                throw new RuntimeException(e);
	            }
	        });
	    }
	    @GetMapping("/mail/success")
	    public List<String> getSuccessMailIds() 
	    {
	    	logger.info("Retrieving successful mail ids");
	        Query query = new Query();
	        query.addCriteria(Criteria.where("statusList.status").is("Success"));
	        List<Notification> notifications = mongoTemplate.find(query, Notification.class);
	        List<String> successMailIds = new ArrayList<>();
	        for (Notification notification : notifications) {
	            successMailIds.add(notification.getTo());
	        }
	        logger.info("Retrieved " + successMailIds.size() + " successful mail ids");
	        return successMailIds;
	    }
	    @GetMapping("/mail/failed")
	    public List<String> getFailedMailIds() {
	        logger.info("Retrieving failed mail ids");
	        Query query = new Query();
	        query.addCriteria(Criteria.where("statusList.status").is("Failed"));
	        List<Notification> notifications = mongoTemplate.find(query, Notification.class);
	        List<String> failedMailIds = new ArrayList<>();
	        for (Notification notification : notifications) {
	            failedMailIds.add(notification.getTo());
	        }
	        logger.info("Retrieved " + failedMailIds.size() + " failed mail ids");
	        return failedMailIds;
	    }

}

