package com.example.spring.jwt.mongodb.scriptshell;

import java.io.IOException;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import com.example.spring.jwt.mongodb.exception.ScriptExecutionException;
import com.example.spring.jwt.mongodb.interfaces.ScriptExecutor;

import java.io.BufferedReader;


@Component
public class WindowsScriptExecutor implements ScriptExecutor {

    private final String interpreter;
    private final Logger logger;

    public WindowsScriptExecutor(String interpreter, Logger logger) {
        this.interpreter = interpreter;
        this.logger = logger;
    }

    @Override
    public String execute(String scriptPath) throws ScriptExecutionException {
        try {
            logger.info("Executing Windows script: {}", scriptPath);
            ProcessBuilder processBuilder = new ProcessBuilder(interpreter, scriptPath);
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
                logger.error("Windows script exited with code " + exitCode);
                throw new ScriptExecutionException("Windows script exited with code " + exitCode);
            }
        } catch (IOException | InterruptedException e) {
            throw new ScriptExecutionException("Windows script execution failed");
        }
        return "Windows Executing";
    }
}


