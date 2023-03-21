package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;

import com.example.demo.entity.Employee;
import com.example.demo.error.EmployeeNotFoundException;

public interface EmployeeService {

	Employee addEmployee(Employee employee) throws EmployeeNotFoundException;

	List<Employee> addListOfEmployee(List<Employee> emList) throws EmployeeNotFoundException;

	Employee updateById(Employee employee, String empId) throws EmployeeNotFoundException;

	List<Employee> fetchListOfEmployee();

	void deleteById(String empId) throws EmployeeNotFoundException;

	List<Employee> fetchByCreateDateBetween(LocalDate startDate, LocalDate endDate);

	Employee fetchById(String empId) throws EmployeeNotFoundException;

	Employee fetchByPhone(String phone) throws EmployeeNotFoundException;

	Employee fetchByEmail(String email) throws EmployeeNotFoundException;

}
