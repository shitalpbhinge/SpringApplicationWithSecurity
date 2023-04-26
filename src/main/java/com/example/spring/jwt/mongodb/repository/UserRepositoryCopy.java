package com.example.spring.jwt.mongodb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring.jwt.mongodb.entity.User_Copy;


public interface UserRepositoryCopy extends JpaRepository<User_Copy, Long>
{

}
