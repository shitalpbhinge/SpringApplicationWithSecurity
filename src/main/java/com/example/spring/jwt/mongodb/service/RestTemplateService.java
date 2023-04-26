package com.example.spring.jwt.mongodb.service;



import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


import org.springframework.http.MediaType;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.spring.jwt.mongodb.models.Employee;
@Service
public class RestTemplateService 
{
	RestTemplate restTemplate=new RestTemplate();
	private static final String get_all_Employee_URL="http://localhost:8080/RestTemplateApi/getAllEmployee";
	private static final String create_Employee_URL="http://localhost:8080/RestTemplateApi/addEmployee";
	private static final String get_EmployeeById_URL="http://localhost:8080/RestTemplateApi/getEmployee";
	private static final String update_Employee_URL="http://localhost:8080/RestTemplateApi/UpdateEmployee";
	private static final String delete_Employee_By_Id_URL="http://localhost:8080/RestTemplateApi/deleteEmployee";

	String username = "shitalbhinge";
	String password = "12345678910";
	String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzaGl0YWxiaGluZ2UiLCJpYXQiOjE2ODI0OTM5MzEsImV4cCI6MTY4MjQ5NTczMX0.DEhZH1VpsDaonygNjluqq3gENOHq4kDxaY-YRY8Jys_VnYJBCqGJb94Z0oV1dStjuKFgNpxE_g44esyzjC4F2w";
	
	
	
	//Method to get all employee
	public ResponseEntity<String> allEmployee()
	{
		HttpHeaders headers=new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setBasicAuth(username, password);//for spring security
		headers.set("Authorization", "Bearer " + token);
		HttpEntity<String> entity=new HttpEntity<String>("parameters",headers);
		ResponseEntity<String> response=restTemplate.exchange(get_all_Employee_URL, HttpMethod.GET, entity, String.class);
		return response;
		
	}
	/*public ResponseEntity<Employee> createEmployee(Employee employee)
	{
		return restTemplate.postForEntity(create_Employee_URL, employee, Employee.class);	
	}*/
	public ResponseEntity<Employee> createEmployee(Employee employee) 
	{
	    HttpHeaders headers = new HttpHeaders();
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setBasicAuth(username, password);
	    headers.set("Authorization", "Bearer " + token);
	    HttpEntity<Employee> request = new HttpEntity<>(employee, headers);
	    return restTemplate.postForEntity(create_Employee_URL, request, Employee.class);
	}
	public Employee getEmployeeById(int employeeId) 
	{
	    // Set up authentication headers
	    HttpHeaders headers = new HttpHeaders();
	    headers.setBearerAuth(token);

	    // Set up request parameters
	    Map<String, Integer> param = new HashMap<>();
	    param.put("employeeId", employeeId);

	    // Create a new HttpEntity with the headers and parameters
	    HttpEntity<Map<String,Integer>> entity = new HttpEntity<>(param, headers);

	    // Make the API call with the authenticated entity
	    ResponseEntity<Employee> response = restTemplate.exchange(get_EmployeeById_URL+ "/"+employeeId, HttpMethod.GET, entity, Employee.class);

	    // Return the Employee object from the response body
	    return response.getBody();
	}


	/*public void  updateEmployee(Employee employee) 
	{
		restTemplate.put(update_Employee_URL,employee);	
	}*/
	/*public void deleteEmployeeById(int employeeId) 
	{
		Map<String, Integer> param=new HashMap<String,Integer>();
		param.put("employeeId", employeeId);
		restTemplate.delete(delete_Employee_By_Id_URL, employeeId);
	}*/
	
	public ResponseEntity<String> deleteEmployeeById(int employeeId) {
	    HttpHeaders headers = new HttpHeaders();
	    headers.setBearerAuth(token);
	    HttpEntity<String> entity = new HttpEntity<>(headers);
	    ResponseEntity<String> response = restTemplate.exchange(delete_Employee_By_Id_URL + "/"+employeeId, HttpMethod.DELETE, entity, String.class, employeeId); 
	    return response;
	}
	public ResponseEntity<Employee> updateEmployee(int employeeId, Employee employeeDetails) 
	{
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    headers.setBearerAuth(token);
	    HttpEntity<Employee> request = new HttpEntity<>(employeeDetails, headers);
	    ResponseEntity<Employee> response = restTemplate.exchange(update_Employee_URL + "/" + employeeId, HttpMethod.PUT, request, Employee.class);
	    return response;
	}


}
