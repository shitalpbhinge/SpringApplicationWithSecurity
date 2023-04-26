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
    
    public void sendSingleSMS(String message, String phoneNumber) {
   // AmazonSNS snsClient = AmazonSNSClient.builder().withRegion(Regions.AP_SOUTH_1).build();
    AmazonSNS snsClient = AmazonSNSClientBuilder.standard().withRegion("ap-south-1")
            .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey))).build();
        Map<String, MessageAttributeValue> smsAttribute = new HashMap<>();
    smsAttribute.put("AWS.SNS.SMS.SenderID", new MessageAttributeValue().withStringValue("myWebsite").withDataType("String"));
    smsAttribute.put("AWS.SNS.SMS.SMSType", new MessageAttributeValue().withStringValue("Transactional").withDataType("String"));
    
    PublishResult result = snsClient.publish(new PublishRequest()
                                    .withMessage(message)
                                    .withPhoneNumber(phoneNumber)
                                    .withMessageAttributes(smsAttribute));
    }
    public void sendMultipleSMS(String message, List<String> phoneNumbers) {
        // AmazonSNS snsClient = AmazonSNSClient.builder().withRegion(Regions.AP_SOUTH_1).build();
        AmazonSNS snsClient = AmazonSNSClientBuilder.standard().withRegion("ap-south-1")
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey))).build();
        Map<String, MessageAttributeValue> smsAttribute = new HashMap<>();
        smsAttribute.put("AWS.SNS.SMS.SenderID", new MessageAttributeValue().withStringValue("myWebsite").withDataType("String"));
        smsAttribute.put("AWS.SNS.SMS.SMSType", new MessageAttributeValue().withStringValue("Transactional").withDataType("String"));
        
        for (String phoneNumber : phoneNumbers) {
            PublishResult result = snsClient.publish(new PublishRequest()
                                        .withMessage(message)
                                        .withPhoneNumber(phoneNumber)
                                        .withMessageAttributes(smsAttribute));
        }
    }

}