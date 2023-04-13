package com.example.spring.jwt.mongodb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.example.spring.jwt.mongodb.entity.Book;
import com.example.spring.jwt.mongodb.exception.CustomDatabaseException;
import com.example.spring.jwt.mongodb.interfaces.Database;



@Repository
public class MongoDatabase implements Database {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Book> getBook() {
        try {
            return mongoTemplate.findAll(Book.class);
        } catch (Exception e) {
            throw new CustomDatabaseException("Error getting books from MongoDB", e);
        }
    }

    @Override
    public Optional<Book> getBookById(String id) {
        try {
            Query query = new Query();
            query.addCriteria(Criteria.where("_id").is(id));
            return Optional.ofNullable(mongoTemplate.findOne(query, Book.class));
        } catch (Exception e) {
            throw new CustomDatabaseException("Error getting book by id from MongoDB", e);
        }
    }

    @Override
    public String addBook(Book book) {
        try {
            mongoTemplate.save(book);
            return book.getId();
        } catch (Exception e) {
            throw new CustomDatabaseException("Error adding book to MongoDB", e);
        }
    }

    @Override
    public String UpdateBook(Book book) {
        try {
            Query query = new Query();
            query.addCriteria(Criteria.where("_id").is(book.getId()));
            Update update = new Update();
            update.set("title", book.getTitle());
            update.set("author", book.getAuthor());
            mongoTemplate.updateFirst(query, update, Book.class);
            return book.getId();
        } catch (Exception e) {
            throw new CustomDatabaseException("Error updating book in MongoDB", e);
        }
    }

    @Override
    public String deleteBook(String id) {
        try {
            Query query = new Query();
            query.addCriteria(Criteria.where("_id").is(id));
            mongoTemplate.remove(query, Book.class);
            return id;
        } catch (Exception e) {
            throw new CustomDatabaseException("Error deleting book from MongoDB", e);
        }
    }
}
