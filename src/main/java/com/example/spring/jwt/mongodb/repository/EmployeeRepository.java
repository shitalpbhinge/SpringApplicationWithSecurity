package com.example.spring.jwt.mongodb.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.example.spring.jwt.mongodb.models.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>
{
	Employee save(Optional<Employee> emp);
}
