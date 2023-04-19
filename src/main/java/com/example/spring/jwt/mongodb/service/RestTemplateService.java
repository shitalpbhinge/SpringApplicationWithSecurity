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
	private static final String get_EmployeeById_URL="http://localhost:8080/RestTemplateApi/getEmployee/{employeeId}";
	private static final String update_Employee_URL="http://localhost:8080/RestTemplateApi/UpdateEmployee";
	private static final String delete_Employee_By_Id_URL="http://localhost:8080/RestTemplateApi/deleteEmployee/{employeeId}";

	//Method to get all employee
	public ResponseEntity<String> allEmployee()
	{
		HttpHeaders headers=new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		//headers.add("Autherization",HeadersToken);//for spring security
		HttpEntity<String> entity=new HttpEntity<String>("parameters",headers);
		ResponseEntity<String> responce=restTemplate.exchange(get_all_Employee_URL, HttpMethod.GET, entity, String.class);
		return responce;
		
	}
	public ResponseEntity<Employee> createEmployee(Employee employee)
	{
		return restTemplate.postForEntity(create_Employee_URL, employee, Employee.class);	
	}
	public Employee getEmployeeById(int employeeId) 
	{
		Map<String, Integer> param=new HashMap<String,Integer>();
		param.put("employeeId", employeeId);
		return restTemplate.getForObject(get_EmployeeById_URL,Employee.class, param);
		// TODO Auto-generated method stub
		
	}
	/*public void  updateEmployee(Employee employee) 
	{
		restTemplate.put(update_Employee_URL,employee);	
	}*/
	public void deleteEmployeeById(int employeeId) 
	{
		Map<String, Integer> param=new HashMap<String,Integer>();
		param.put("employeeId", employeeId);
		restTemplate.delete(delete_Employee_By_Id_URL, employeeId);
	}
	public void updateEmployee(int employeeId, Employee employeeDetails) {
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);

	    HttpEntity<Employee> request = new HttpEntity<>(employeeDetails, headers);

	    ResponseEntity<String> response = restTemplate.exchange(update_Employee_URL + "/" + employeeId, HttpMethod.PUT, request, String.class);

	    if (response.getStatusCode() == HttpStatus.OK) {
	        System.out.println("Employee updated");
	    } else {
	        System.out.println("Failed to update employee");
	    }
	}


}
