package com.example.spring.jwt.mongodb.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.spring.jwt.mongodb.interfaces.ScriptExecutor;
import com.example.spring.jwt.mongodb.scriptshell.PythonScriptExecutor;
import com.example.spring.jwt.mongodb.scriptshell.UnixScriptExecutor;
import com.example.spring.jwt.mongodb.scriptshell.WindowsScriptExecutor;


@Component
public class ScriptExecutionFactory 
{
	@Autowired
	PythonScriptExecutor pythonExecutor;
	
	@Autowired
	WindowsScriptExecutor windowsExecutor;
	
	@Autowired
	UnixScriptExecutor unixExecutor;

	public ScriptExecutor getExecutor(String type) throws Exception 
	{
        if (type.equals("python")) 
        {
            return pythonExecutor;
            
        } 
        else if (type.equals("unix")) 
        {
            return unixExecutor;
            
        } 
        else if (type.equals("windows")) 
        {
            return windowsExecutor;
            
        } 
        else 
        {
            throw new Exception("Unsupported platform: " + type);
        }
    }
}
