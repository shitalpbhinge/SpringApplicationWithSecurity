package com.example.spring.jwt.mongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

import com.example.spring.jwt.mongodb.entity.Book;


@Repository
public interface BookRepositoryMongo extends MongoRepository<Book, String> 
{

}
