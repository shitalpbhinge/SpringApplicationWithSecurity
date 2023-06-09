package com.example.spring.jwt.mongodb.service;
import java.util.Properties;
import javax.mail.internet.MimeMessage;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeBodyPart;
import java.io.File;


import org.springframework.stereotype.Service;

@Service
public class EmailService 
{
	public boolean sendEmail(String subject, String message, String to)
	{
	    boolean foo = false; // Set the false, default variable "foo", we will allow it after sending code process email

	    String senderEmail = "shital.shivajipoly@gmail.com"; // your gmail email id
	    String senderPassword = "vbvzdwuigrbpflfy"; // your gmail id password

	    // Properties class enables us to connect to the host SMTP server
	    Properties properties = new Properties();

	    // Setting necessary information for object property

	    // Setup host and mail server
	    properties.put("mail.smtp.auth", "true"); // enable authentication
	    properties.put("mail.smtp.starttls.enable", "true"); // enable TLS-protected connection
	    properties.put("mail.smtp.host", "smtp.gmail.com"); // Mention the SMTP server address. Here Gmail's SMTP server is being used to send email
	    properties.put("mail.smtp.port", "587"); // 587 is TLS port number

	    // get the session object and pass username and password
	    Session session = Session.getDefaultInstance(properties, new Authenticator()
	    {
	        protected PasswordAuthentication getPasswordAuthentication(){

				return new PasswordAuthentication(senderEmail, senderPassword);
	        }
	    });

	    try {

	        MimeMessage msg = new MimeMessage(session); // Create a default MimeMessage object for compose the message

	        msg.setFrom(new InternetAddress(senderEmail)); // adding sender email id to msg object

	        msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to)); // adding recipient to msg object

	        msg.setSubject(subject); // adding subject to msg object
	        msg.setText(message); // adding text to msg object

	        Transport.send(msg); // Transport class send the message using send() method
	        System.out.println("Email Sent Wtih Attachment Successfully...");

	        foo = true; // Set the "foo" variable to true after successfully sending emails

	    }catch(Exception e){

	            System.out.println("EmailService File Error"+ e);
	    }

	    return foo; // and return foo variable
	 }	
}
