package com.example.spring.jwt.mongodb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring.jwt.mongodb.models.Employee;
import com.example.spring.jwt.mongodb.service.RestTemplateService;

@RestController
@RequestMapping("/RestTemplate")
public class RestTemplateController 
{
	@Autowired
	private RestTemplateService restTemplateservice;
	
	@GetMapping("/getAllEmployee")
	public ResponseEntity<String> getAllEmployee()
	{
		return restTemplateservice.allEmployee();
	}
	@PostMapping("/addEmployee")
	public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee)
	{
		return restTemplateservice.createEmployee(employee);
	}
	@GetMapping("/getEmployee/{employeeId}")
	public Employee getEmployeeById(@PathVariable int employeeId)
	{
		return restTemplateservice.getEmployeeById(employeeId);
	}
	@PutMapping("/UpdateEmployee/{employeeId}")
	public void UpdateEmployeeById(@PathVariable int employeeId ,@RequestBody Employee employee)
	{
		 restTemplateservice.updateEmployee(employeeId,employee);
	}
	@DeleteMapping("/deleteEmployee/{employeeId}")
	public void deleteEmployeeById(@PathVariable int employeeId)
	{
		 restTemplateservice.deleteEmployeeById(employeeId);
	}
}
