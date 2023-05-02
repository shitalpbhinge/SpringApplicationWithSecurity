package com.example.spring.jwt.mongodb.AWSMessageSNS;

import java.util.Date;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import org.apache.spark.sql.execution.datasources.json.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
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
	    public static final String arn = "arn:aws:sns:ap-south-1:736331216941:gopal";
	    
	    
	 //  @Scheduled(cron = "0 0 13 1/1 * ?")
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
	
	/*    public void sendMultipleSMS(String message, String[] phoneNumbers) {

	        if (message == null || message.isEmpty()) {
	            throw new IllegalArgumentException("Message cannot be null or empty");
	        }

	        if (phoneNumbers == null || phoneNumbers.length == 0) {
	            throw new IllegalArgumentException("Phone numbers cannot be null or empty");
	        }

	        AWSStepFunctions client = AWSStepFunctionsClientBuilder.standard()
	                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey)))
	                .withRegion("ap-south-1")
	                .build();

	        String stateMachineArn = "arn:aws:states:ap-south-1:123456789012:stateMachine:SMSStateMachine";

	        Map<String, Object> input = new HashMap<>();
	        input.put("message", message);
	        input.put("phoneNumbers", phoneNumbers);

	        StartExecutionRequest request = new StartExecutionRequest()
	                .withStateMachineArn(stateMachineArn)
	                .withInput(JsonUtils.toJsonString(input));

	        try {
	            StartExecutionResult result = client.startExecution(request);
	            System.out.println("State machine execution started. Execution ARN: " + result.getExecutionArn());
	        } catch (Exception e) {
	            System.err.println("Error occurred while starting the state machine execution");
	            e.printStackTrace();
	        }
	    }*/

	    public void sendMultipleSMS(String message, String[] phoneNumbers) 
	    {
		
		    if (message == null || message.isEmpty()) 
		    {
		        throw new IllegalArgumentException("Message cannot be null or empty");
		    }
		
		    if (phoneNumbers == null || phoneNumbers.length == 0) 
		    {
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
		
		    for (String phoneNumber : phoneNumbers) 
		    {
		        if (phoneNumber == null || phoneNumber.isEmpty()) {
		            System.err.println("Skipping null or empty phone number");
		            continue;
		        }
		        request.setPhoneNumber(phoneNumber);
		
		        try 
		        {
		            PublishResult result = snsClient.publish(request);
		            System.out.println("SMS sent to " + phoneNumber + ". Message ID: " + result.getMessageId());
		        } 
		        catch (Exception e) 
		        {
		            System.err.println("Error occurred while sending the SMS message to " + phoneNumber);
		            e.printStackTrace();
		        } 
		    }
		}
		
	  /*  public void sendScheduledMessage(String message, String phoneNumber, Date deliveryTime) {
	        
	        AmazonSNS snsClient = AmazonSNSClientBuilder.standard()
	                .withRegion("ap-south-1")
	                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey)))
	                .build();

	        Map<String, MessageAttributeValue> smsAttributes = new HashMap<>();
	        smsAttributes.put("AWS.SNS.SMS.SMSType", new MessageAttributeValue().withStringValue("Transactional").withDataType("String"));
	       // smsAttributes.put("AWS.SNS.MOBILE.AWS_TIMESTAMPTZ", new MessageAttributeValue().withStringValue(deliveryTime.toString()).withDataType("String"));
	        smsAttributes.put("DeliveryTime", new MessageAttributeValue().withStringValue(deliveryTime.toString()).withDataType("String"));
	        PublishRequest publishRequest = new PublishRequest()
	                .withMessage(message)
	                .withPhoneNumber(phoneNumber)
	                .withMessageAttributes(smsAttributes);

	        publishRequest.setMessageAttributes(smsAttributes);
	        
	     //   publishRequest.withDelaySeconds((int) TimeUnit.SECONDS.convert(deliveryTime.getTime() - System.currentTimeMillis(), TimeUnit.MILLISECONDS));

	        PublishResult publishResult = snsClient.publish(publishRequest);

	        System.out.println("Scheduled message sent with message id: " + publishResult.getMessageId());
	    }*/

}