package com.example.spring.jwt.mongodb.controllers;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.spring.jwt.mongodb.entity.FileUploadHelper;

@CrossOrigin("http://localhost:8080")
@RestController
@RequestMapping("/apifile")
public class FileUploadDownloadController 
{
	private static final Logger logger = LoggerFactory.getLogger(FileUploadDownloadController.class);
	@Autowired
	private FileUploadHelper fileUploadHelper;
	private final ResourceLoader resourceLoader;

	 @Autowired
	    public FileUploadDownloadController(ResourceLoader resourceLoader) {
	        this.resourceLoader = resourceLoader;
	    }
	@PostMapping("/uploadFile")
	public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file)
	{
		
		try
		{
			//validation
			if(file.isEmpty())
			{
				logger.warn("Request must contain a file");
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Request must contain a file");
			}
			//
			if(file.getContentType().equals("image/jpeg"))
			{
				//logger.info("only jpeg");
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("only jpeg");
			}
			//upload file
			boolean f=fileUploadHelper.uploadFile(file);
			if(f) 
			{
				logger.info("File uploaded ");
				return ResponseEntity.ok("File Uploaded.....");
			}
		
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		logger.error("try again");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("try agrain...");
		
	}
	@GetMapping("/downloadFile")
	public ResponseEntity<Resource> downloadFile() throws IOException {
	    String filePath = "/home/inferyx/Downloads/MySqlCurd/src/main/resources/static/image/bc.txt";
	    Resource resource = new FileSystemResource(filePath);
	    //File file = resource.getFile();

	    if (!resource.exists()) 
	    {
	    	logger.warn("File not found");
	        throw new FileNotFoundException("File not found");
	    }

	    HttpHeaders headers = new HttpHeaders();
	    headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + resource.getFilename());

	    return ResponseEntity.ok()
	            
	           // .contentLength(file.length())
	            .contentType(MediaType.APPLICATION_OCTET_STREAM)
	            .headers(headers)
	            .body(resource);
	}

	@ExceptionHandler(FileNotFoundException.class)
	public ResponseEntity<String> handleFileNotFoundException(FileNotFoundException ex) {
	   return ResponseEntity.status(HttpStatus.NOT_FOUND).body("File not found");
	}
	@GetMapping("/listDirectory")
	public List<String> listDirectoryContents(@RequestParam(name = "path", defaultValue = ".") String path) {
	    File dir = new File(path);
	    File[] files = dir.listFiles();

	    List<String> directoryContents = new ArrayList<>();

	    for (File file : files) {
	        if (file.isDirectory()) {
	            directoryContents.add("DIR: " + file.getName());
	        } else {
	            directoryContents.add("FILE: " + file.getName());
	        }
	    }

	    return directoryContents;
	}
	@GetMapping("/directoryStructure")
	public List<List<String>> getDirectory(@RequestParam("path") String path) throws Exception {
	   
	logger.info("inside method get directory");
	   
	 //logger.info("acquiring directory path");
	    File directory = new File(path);
	// logger.info("directory path acquired");
	 logger.info("Getting contents of directory");
	    String[] contents = directory.list();
	    if (contents == null) {
	  logger.error("Failed to acquire content of directory");
	    }
	   logger.info("content acquired");
	    List<String> folders = new ArrayList<>();
	    List<String> files = new ArrayList<>();
	   logger.info("sorting content into file and directory");
	    for (String content : contents) {
	        File f = new File(directory, content);
	        if (f.isDirectory()) {
	            folders.add(content);
	        } else if (f.isFile()) {
	            files.add(content);
	        }
	    }
	    List<List<String>> result = new ArrayList<>();
	    result.add(folders);
	    result.add(files);
	   logger.info("Sorting success");
	    return result;
	}


}
