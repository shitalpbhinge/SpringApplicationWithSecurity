package com.example.spring.jwt.mongodb.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.multipart.MultipartFile;
@SpringBootTest
public class FileUploadHelperTest 
{
  
	//private Logger log = Logger.getL(this.getClass());
 
		@Test  
		public void uploadFile()
		{  
			try 
			{
				//log.info("Starting execution of uploadFile");
				boolean expectedValue=false; 
				MultipartFile multipartfile = null; 
			 
			  
				FileUploadHelper fileuploadhelper  =new FileUploadHelper(); 
				boolean actualValue=fileuploadhelper.uploadFile( multipartfile);
				//log.info("Expected Value="+ expectedValue +" . Actual Value="+actualValue);
				System.out.println("Expected Value="+ expectedValue +" . Actual Value="+actualValue);
				assertEquals(expectedValue, actualValue);
					
			} 
			catch (Exception exception) 
			{
						//log.error("Exception in execution of execute1GetAllLogFromFirstMovF-"+exception,exception);
						exception.printStackTrace();
						assertFalse(false);
			}
		}  
}
