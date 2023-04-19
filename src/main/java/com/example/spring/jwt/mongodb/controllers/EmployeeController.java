package com.example.spring.jwt.mongodb.controllers;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring.jwt.mongodb.models.Employee;
import com.example.spring.jwt.mongodb.service.EmployeeService;


@RestController
@RequestMapping("/RestTemplateApi")
public class EmployeeController 
{
	private static final Logger logger = LoggerFactory.getLogger(EmailController .class);

	@Autowired
	private EmployeeService empService;

	@PostMapping("/addEmployee")
	public Employee addEmployee(@RequestBody Employee employee)
	{
		logger.info("Adding Employee");
		return empService.addEmployee(employee);
	}
	@GetMapping("/getAllEmployee")
	public List<Employee> getEmployee()
	{
		logger.info("Get All Employee");
		return empService.getEmployee();
	}
	@GetMapping("/getEmployee/{employeeId}")
	public Optional<Employee>getEmployeeById(@PathVariable int employeeId)
	{
		logger.info("getting Employee By Id");
		return empService.getEmployee(employeeId);
	}
	@PutMapping("/UpdateEmployee/{id}")
	public Employee UpdateEmployeeById(@PathVariable int id ,@RequestBody Employee employee)
	{
		logger.info("Updatting Employee details");
		return empService.updateEmployee(id, employee);
	}
	@DeleteMapping("/deleteEmployee/{employeeId}")
	public Optional<Employee>deleteEmployeeById(@PathVariable int employeeId)
	{
		logger.info("Deleting Employee");
		return empService.deleteEmployee(employeeId);
	}
}
