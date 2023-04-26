package com.example.spring.jwt.mongodb.controllers;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.spring.jwt.mongodb.entity.UserDemo;
import com.example.spring.jwt.mongodb.kafka.KafkaProducerDemo;
import com.example.spring.jwt.mongodb.repository.UserRepositoryDemo;


@CrossOrigin("http://localhost:8080")
@RestController
@RequestMapping("/v1/mysqlapp")
public class UserController 
{
	@Autowired
	UserRepositoryDemo userRepositorydemo;
	
	@Autowired
	KafkaProducerDemo kafkaProducer;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Scheduled(fixedDelay = 10000)
	@GetMapping("/publish")
	public String publishBookData() 
	{
		kafkaProducer.publishBookData();
		return("user copy published");
	}
	@GetMapping("/getAllBooks")
	public List<UserDemo> getAllUsers()
	{
		return userRepositorydemo.findAll();
	}
	@GetMapping("/getUserById/{id}")
	public Optional<UserDemo> getUserById(@PathVariable(value="id") long id)
	{
		return userRepositorydemo.findById(id);
	}	
	@PostMapping("/users")
    public ResponseEntity<?> addUser(@RequestBody UserDemo user)
    {
        try
        {
            UserDemo createdUser = userRepositorydemo.save(new UserDemo(user.getId(),user.getName(),user.getEmail()));
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	@PutMapping("/updateUser/{id}")
	public UserDemo updateUser(@PathVariable(value="id") long id, @RequestBody UserDemo userDetails)
	{
		
			Optional<UserDemo> user = userRepositorydemo.findById(id);
			UserDemo user_new=user.get();
			user_new.setName(userDetails.getName());
			user_new.setEmail(userDetails.getEmail());
			return userRepositorydemo.save(user_new);
	
	}
	@DeleteMapping("/deleteUser/{id}")
	public void deleteUser(@PathVariable(value="id") long id)
	{
			Optional<UserDemo> user=userRepositorydemo.findById(id);
			UserDemo user_new=user.get();
			userRepositorydemo.delete(user_new);
	}
}
