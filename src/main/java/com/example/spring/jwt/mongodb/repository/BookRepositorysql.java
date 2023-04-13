package com.example.spring.jwt.mongodb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.example.BookDesignFactoryMethod.entity.Book;
@Repository
public interface BookRepositorysql extends JpaRepository<Book, String>{
}
