package com.example.spring.jwt.mongodb.kafka;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import com.example.spring.jwt.mongodb.entity.UserDemo;
import com.example.spring.jwt.mongodb.entity.User_Copy;
import com.example.spring.jwt.mongodb.repository.UserRepositoryCopy;
@Component
public class KafkaConsumerDemo 
{
		@Autowired
		UserRepositoryCopy userRepositoryCopy;
		
		@Autowired
		JdbcTemplate jdbcTemplate;
		
		@KafkaListener(topics = "my-topic")
	    public void consume(UserDemo user) 
		{
			   User_Copy userc=new User_Copy();
			   
			   userc.setId(user.getId());
			   userc.setName(user.getName());
			   userc.setEmail(user.getEmail());
			
			   userRepositoryCopy.save(userc); 
	    }
}
