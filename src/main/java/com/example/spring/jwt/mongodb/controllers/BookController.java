package com.example.spring.jwt.mongodb.controllers;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring.jwt.mongodb.entity.Book;
import com.example.spring.jwt.mongodb.factory.DatabaseType;
import com.example.spring.jwt.mongodb.interfaces.Database;


@CrossOrigin("http://localhost:8080")
@RestController
@RequestMapping("/api")
public class BookController 
{
	private static final Logger logger = LoggerFactory.getLogger(BookController .class);
	
	@Autowired
	DatabaseType dbtype;
	
	@GetMapping
	@ResponseBody
	public List<Book> getBooks(@RequestParam("type") String type) {
	    try {
	        logger.info("Getting All Books");
	        Database db = dbtype.getBookRepository(type);
	        List<Book> books = db.getBook();
	        return books;
	    } catch(Exception e) {
	        logger.error("Exception occurred while getting books: ", e);
	        return Collections.emptyList();
	    }
	}
	@PostMapping("/add/{type}")
	@ResponseBody
	public ResponseEntity<String> addBook(@RequestBody Book book, @PathVariable String type) {
	    try {
	        logger.info("Adding Book");
	        Database db = dbtype.getBookRepository(type);
	        db.addBook(book);
	        return ResponseEntity.ok("Book added");
	    } catch (Exception e) {
	        logger.error("Exception occurred while adding book: ", e);
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add Book");
	    }
	}

	/*@GetMapping
	public String getBooks(@RequestParam("type") String type) 
	{
		try {
			logger.info("Getting All Books");
	        Database db = dbtype.getBookRepository(type);
	         db.getBook();
	         return "Getting Books";
		}
		catch(Exception e) {
			logger.error("Exception occurred while getting books: ", e);
			return "Failed to get Books";
		}
	}*/
	/*@PostMapping("/add/{type}")
	public String addBook(@RequestBody Book book,@PathVariable String type) {
		try {
			logger.info("Adding Book");
			Database db=dbtype.getBookRepository(type);
	        db.addBook(book);
	        return "Book added";
		}
		catch(Exception e) {
			logger.error("Exception occurred while adding book: ", e);
			return "Failed to add Book";
		}
	}*/
	@GetMapping("/{id}")
	@ResponseBody
	public ResponseEntity<Optional<Book>> getBookById(@PathVariable String id, @RequestParam("type") String type) {
	    try {
	        logger.info("Getting Book by id");
	        Database db = dbtype.getBookRepository(type);
	        Optional<Book> book = db.getBookById(id);
	        return ResponseEntity.ok(book);
	    } catch (Exception e) {
	        logger.error("Exception occurred while getting book by id: ", e);
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    }
	}

	/*@GetMapping("/{id}")
	 public String getUserById(@PathVariable String id, @RequestParam("type") String type)
	 {
		try {
			logger.info("Getting Book by id");
	        Database db = dbtype.getBookRepository(type);
	        db.getBookById(id);
	        return "Getting Book by id";
		}
		catch(Exception e) {
			logger.error("Exception occurred while getting book by id: ", e);
			return "Failed to get Book by id";
		}
	 }*/

	@PutMapping("/{id}/{type}")
	 public String updateUser(@PathVariable String id, @PathVariable String type, @RequestBody Book book)
	 {
		 try {
			 logger.info("Updating Book");
		        Database db = dbtype.getBookRepository(type);
		        db.UpdateBook(book);
		        return "Book updated";
		 }
		 catch(Exception e) {
			 logger.error("Exception occurred while updating book: ", e);
			 return "Failed to update Book";
		 }
	 }
	 
	@DeleteMapping("/{id}/{type}")
	public String deleteUser(@PathVariable String id, @PathVariable String type) {
		try {
			logger.info("Deleting book");
			Database db = dbtype.getBookRepository(type);
	        db.deleteBook(id);
	        return "Book deleted";
		}
		catch(Exception e) {
			logger.error("Exception occurred while deleting book: ", e);
			return "Failed to delete Book";
		}
	}
}
