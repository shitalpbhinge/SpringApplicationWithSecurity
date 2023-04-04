package com.example.spring.jwt.mongodb.service;


import java.util.ArrayList;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import com.example.spring.jwt.mongodb.models.Notification;
import com.example.spring.jwt.mongodb.models.StatusList;


@Service
public class ThreadService {

    @Autowired
    private JavaMailSender javaMailSender;
    
    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private StatusList statusList;
    private static final Logger logger = LoggerFactory.getLogger(ThreadService.class);
    public void sendMail(String email, String subject, String body) throws Exception
    {
       
    	logger.info("Sending mail to " + email);
        SimpleMailMessage helper=new SimpleMailMessage();
        helper.setFrom("shital.shivajipoly@gmail.com");
        helper.setTo(email);
        helper.setSubject(subject);
        helper.setText(body);
        javaMailSender.send(helper);
        logger.info("Mail sent successfully to " + email);
    }
    public Callable<List<Notification>> sendMailAsync(List<String> toList, String subject, String body) {
        return new Callable<List<Notification>>() {
            @Override
            public List<Notification> call() throws Exception {
                List<Notification> notifications = new ArrayList<>();
                for (String email : toList) {
                    Notification mail = new Notification();
                    mail.setFrom("shital.shivajipoly@gmail.com");
                    mail.setTo(email);
                    mail.setDate(new Date());
                    StatusList statusList = new StatusList();
                    try {
                        sendMail(email, subject, body);
                        statusList.setStatus("Success");
                        logger.info("Mail sent successfully to: {}", email);
                    } catch (Exception e) {
                        statusList.setStatus("Failed");
                        logger.error("Failed to send mail to: {}", email, e);
                    }
                    mail.setStatusList(Arrays.asList(statusList));
                    mongoTemplate.save(mail);
                    notifications.add(mail);
                }
                return notifications;
            }
        };
    }

}
    
