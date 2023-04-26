package com.example.spring.jwt.mongodb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.spring.jwt.mongodb.entity.UserDemo;
public interface UserRepositoryDemo extends JpaRepository<UserDemo, Long>
{

}
