package com.example.spring.jwt.mongodb.kafka;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import com.example.spring.jwt.mongodb.entity.UserDemo;
import com.example.spring.jwt.mongodb.repository.UserRepositoryDemo;
@Component
public class KafkaProducerDemo 
{
		@Autowired
		private KafkaTemplate<String, UserDemo> kafkaTemplate;
				
		@Autowired
		UserRepositoryDemo userRepositorydemo;
		
		public void publishBookData() 
		{
				List<UserDemo> users = userRepositorydemo.findAll();
				for (UserDemo user : users) 
				{
					kafkaTemplate.send("my-topic", user);
				}
		}		
}
