package com.example.spring.jwt.mongodb.scriptshell;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.spring.jwt.mongodb.exception.ScriptExecutionException;
import com.example.spring.jwt.mongodb.factory.ScriptExecutionFactory;
import com.example.spring.jwt.mongodb.interfaces.ScriptExecutor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


@Component
public class PythonScriptExecutor implements ScriptExecutor 
{
	@Autowired
	ScriptExecutionFactory scriptExecutor;
   
	private final String interpreter;
    private final Logger logger;
    
    public PythonScriptExecutor(String interpreter, Logger logger) 
    {
        this.interpreter = interpreter;
        this.logger = logger;
    }

    @Override
    public String execute(String scriptPath) throws ScriptExecutionException {
        try {
            logger.info("Executing Python script: {}", scriptPath);
            ProcessBuilder processBuilder = new ProcessBuilder("python3", scriptPath);
            logger.info("ProcessBuilder Started");
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();
            logger.info("Process Started");
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            logger.info("reading Started");
            int exitCode = process.waitFor();
            String line;
            while ((line = reader.readLine()) != null) {
                logger.info(line);
            }
            if (exitCode != 0) {
                logger.error("Python script exited with code " + exitCode);
                throw new ScriptExecutionException("Python script exited with code " + exitCode);
            }
        } catch (IOException | InterruptedException e) {
            throw new ScriptExecutionException("Python script execution failed");
        }
        return "python Executing";
    }
}
