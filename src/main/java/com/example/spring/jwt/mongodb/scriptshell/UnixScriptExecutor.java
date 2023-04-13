package com.example.spring.jwt.mongodb.scriptshell;

import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import com.example.SciptShell.factory.ScriptExecutionException;
import com.example.SciptShell.service.ScriptExecutor;
@Component
public class UnixScriptExecutor implements ScriptExecutor {
	private static final Logger logger = LoggerFactory.getLogger(UnixScriptExecutor .class);
	
    @Override
    public String execute(String scriptPath) throws ScriptExecutionException {
        // Implementation for executing a Unix script
        try 
        {
            logger.info("Executing Unix script: {}", scriptPath);
            ProcessBuilder processBuilder = new ProcessBuilder("bash", scriptPath);
            logger.info("ProcessBuilder Started");
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();
            logger.info("Process Started");
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            logger.info("reading Started");
            int exitCode = process.waitFor();
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            if (exitCode != 0) {
                logger.error("Unix script exited with code " + exitCode);
                throw new Exception("Unix script exited with code " + exitCode);
            }
        } catch (Exception e) {
            throw new ScriptExecutionException("Unix script execution failed");
        }
        return "Unix script executed successfully";
    }
}
