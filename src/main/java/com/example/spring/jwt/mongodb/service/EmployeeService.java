package com.example.spring.jwt.mongodb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spring.jwt.mongodb.models.Employee;
import com.example.spring.jwt.mongodb.repository.EmployeeRepository;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public List<Employee> getEmployee() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> getEmployee(int employeeId) {
        return employeeRepository.findById(employeeId);
    }

    public Employee updateEmployee(int id,Employee employeeDetails) 
    {
    	Optional<Employee> emp = employeeRepository.findById(id);
		Employee emp_new=emp.get();
		emp_new.setName(employeeDetails.getName());
		emp_new.setDepartment(employeeDetails.getDepartment());
		return employeeRepository.save(emp_new);
    }

    public Optional<Employee> deleteEmployee(int employeeId) {
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        if (employee.isPresent()) {
            employeeRepository.deleteById(employeeId);
        }
        return employee;
    }
}
