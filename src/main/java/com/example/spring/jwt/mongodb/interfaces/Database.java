package com.example.spring.jwt.mongodb.interfaces;

import java.util.List;
import java.util.Optional;

import com.example.BookDesignFactoryMethod.entity.Book;

public interface Database 
{
	List<Book> getBook();
	Optional<Book> getBookById(String id);
	String addBook(Book book);
	String UpdateBook(Book book);
	String deleteBook(String id);
}
