package com.example.spring.jwt.mongodb.AWSMessageSNS;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.stereotype.Service;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import com.amazonaws.services.sns.model.MessageAttributeValue;

@Service
public class AWSClient 
{
	 public static final String accessKey = "AKIA2W4GCXAW4VJC67FN";
	    public static final String secretKey = "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";
	    public static final String arn = "arn:aws:sns:ap-south-1:736331216941:iscolto";
	    public void sendSingleSMS(String message, String phoneNumber) 
	    {
			  
			    AmazonSNS snsClient = AmazonSNSClientBuilder.standard().withRegion("ap-south-1")
			            .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey))).build();
			        
			    Map<String, MessageAttributeValue> smsAttribute = new HashMap<>();
			 //   smsAttribute.put("AWS.SNS.SMS.SenderID", new MessageAttributeValue().withStringValue("myWebsite").withDataType("String"));
			    smsAttribute.put("AWS.SNS.SMS.SMSType", new MessageAttributeValue().withStringValue("Transactional").withDataType("String"));
			    
			    PublishResult result = snsClient.publish(new PublishRequest()
			                                    .withMessage(message)
			                                    .withPhoneNumber(phoneNumber)
			                                    .withMessageAttributes(smsAttribute));
	    }

		public void sendMultipleSMS(String message, String[] phoneNumbers) {
		
		    if (message == null || message.isEmpty()) {
		        throw new IllegalArgumentException("Message cannot be null or empty");
		    }
		
		    if (phoneNumbers == null || phoneNumbers.length == 0) {
		        throw new IllegalArgumentException("Phone numbers cannot be null or empty");
		    }
		
		    AmazonSNS snsClient = AmazonSNSClientBuilder.standard().withRegion("ap-south-1")
		            .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey))).build();
		      
		
		    Map<String, MessageAttributeValue> smsAttributes = new HashMap<>();
		    smsAttributes.put("AWS.SNS.SMS.SMSType", new MessageAttributeValue()
		            .withStringValue("Transactional")
		            .withDataType("String"));
		
		    PublishRequest request = new PublishRequest()
		            .withMessage(message)
		            .withMessageAttributes(smsAttributes);
		
		    for (String phoneNumber : phoneNumbers) {
		        if (phoneNumber == null || phoneNumber.isEmpty()) {
		            System.err.println("Skipping null or empty phone number");
		            continue;
		        }
		        request.setPhoneNumber(phoneNumber);
		
		        try {
		            PublishResult result = snsClient.publish(request);
		            System.out.println("SMS sent to " + phoneNumber + ". Message ID: " + result.getMessageId());
		        } catch (Exception e) {
		            System.err.println("Error occurred while sending the SMS message to " + phoneNumber);
		            e.printStackTrace();
		        } 
		        }
		    }
		
		/*public void sendMultipleSMSOTP(String[] phoneNumbers) {
		    
		    String verificationCode = generateVerificationCode(); // Generate a random verification code
		    
		    String message = "Your verification code is: " + verificationCode; // Create the message string with the verification code
		    
		    AmazonSNS snsClient = AmazonSNSClientBuilder.standard().withRegion("ap-south-1")
		            .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey))).build();
		      
		    Map<String, MessageAttributeValue> smsAttributes = new HashMap<>();
		    smsAttributes.put("AWS.SNS.SMS.SMSType", new MessageAttributeValue()
		            .withStringValue("Transactional")
		            .withDataType("String"));
		    
		    PublishRequest request = new PublishRequest()
		            .withMessage(message)
		            .withMessageAttributes(smsAttributes);
		    
		    for (String phoneNumber : phoneNumbers) {
		        if (phoneNumber == null || phoneNumber.isEmpty()) {
		            System.err.println("Skipping null or empty phone number");
		            continue;
		        }
		        request.setPhoneNumber(phoneNumber);
		    
		        try {
		            PublishResult result = snsClient.publish(request);
		            System.out.println("SMS sent to " + phoneNumber + ". Message ID: " + result.getMessageId());
		        } catch (Exception e) {
		            System.err.println("Error occurred while sending the SMS message to " + phoneNumber);
		            e.printStackTrace();
		        } 
		    }
		}

		private String generateVerificationCode() {
		    // Generate a random 6-digit verification code
		    int code = (int) (Math.random() * 900000) + 100000;
		    return String.valueOf(code);
		}*/

}