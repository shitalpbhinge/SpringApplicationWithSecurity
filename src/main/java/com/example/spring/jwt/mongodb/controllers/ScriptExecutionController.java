package com.example.spring.jwt.mongodb.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.SciptShell.factory.ScriptExecutionException;
import com.example.SciptShell.factory.ScriptExecutionFactory;
import com.example.SciptShell.service.ScriptExecutor;

@RestController
@RequestMapping("/api")
public class ScriptExecutionController 
{
	@Autowired
	ScriptExecutionFactory scriptExecutor;

    private static final Logger LOGGER = LoggerFactory.getLogger(ScriptExecutionController.class);

   @PostMapping("/executeScript")
    public String executeScript(@RequestParam String type, @RequestParam String filepath) throws Exception {
        try 
        {
            ScriptExecutor executor = scriptExecutor.getExecutor(type);
           // executor.execute(filepath);
            String output = executor.execute(filepath);
           // LOGGER.info("Script execution successful: type={}, filepath={}", type, filepath);
            return "File executed";
        } 
        catch (Exception e) 
        {
            LOGGER.error("Platform not supported: type={}, filepath={}", type, filepath, e);
            throw new Exception("Platform not supported", e);
        }
    }
    
}
