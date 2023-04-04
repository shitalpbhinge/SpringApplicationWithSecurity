package com.example.spring.jwt.mongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.spring.jwt.mongodb.models.Notification;



public interface ThreadRepository extends MongoRepository<Notification, String>
{

}
