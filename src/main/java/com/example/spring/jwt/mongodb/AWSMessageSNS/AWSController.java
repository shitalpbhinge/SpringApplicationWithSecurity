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
    /*@GetMapping("/sms/send")
    public String sendSMS(@RequestParam("message") String message, @RequestParam("phoneNumber") String phoneNumber) {
        // Call the sendSingleSMS method with the message and phoneNumber parameters
        awsSNSClient.sendSingleSMS(message, phoneNumber);
        return "SMS sent successfully";
    }
    @GetMapping("/send-multiple-sms")
    public ResponseEntity<String> sendMultipleSMS() {
        String message = "Hello from my website!";
        List<String> phoneNumbers = Arrays.asList("7387403654", "9122415905", "5551234567");
        
        try {
        	 awsSNSClient. sendMultipleSMS(message, phoneNumbers);
            return ResponseEntity.ok("SMS messages sent successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error sending SMS messages: " + e.getMessage());
        }
    }*/

}