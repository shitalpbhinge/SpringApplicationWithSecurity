package com.example.spring.jwt.mongodb.AWSMessageSNS;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sms")
public class AWSController 
{
	 @Autowired
	    private AWSClient awsSNSClient;
	    @GetMapping("/send")
	    public ResponseEntity<String> sendMessage(@RequestParam("message")String message,@RequestParam("phoneNumber")String phoneNumber){
	    try
	    {
	            awsSNSClient.sendSingleSMS(message,phoneNumber);
	            return ResponseEntity.ok("Message Sent successfully");
	    }
	    catch(Exception e)
	    {
	            e.getStackTrace();
	            e.toString();
	            return (ResponseEntity<String>) ResponseEntity.internalServerError();
	        }
	    }
	    
	    @GetMapping("/sendmultiple")
	    public ResponseEntity<String> sendMessage(@RequestParam("message")String message,@RequestParam("phoneNumber")String[] phoneNumber){
	    try
	    {
	            awsSNSClient.sendMultipleSMS(message,phoneNumber);
	            return ResponseEntity.ok("Message Sent successfully");
	    }
	    catch(Exception e)
	    {
	            e.getStackTrace();
	            e.toString();
	            return (ResponseEntity<String>) ResponseEntity.internalServerError();
	        }
	    }

}