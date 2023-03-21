package com.example.demo.controller;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Employee;
import com.example.demo.error.EmployeeNotFoundException;
import com.example.demo.service.EmployeeService;

@RestController
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;
	private final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

	@PostMapping("/addemployee")
	public Employee addEmployee(@RequestBody Employee employee) throws EmployeeNotFoundException {
		LOGGER.info("Inside addEmployee of EmployeeController");
		return employeeService.addEmployee(employee);
	}

	@PostMapping("/addlistemp")
	public List<Employee> addListOfEmployee(@RequestBody List<Employee> emList) throws EmployeeNotFoundException {
		LOGGER.info("Inside addListOfEmployee of EmployeeController");
		return employeeService.addListOfEmployee(emList);

	}

	@PutMapping("/employee/{id}")
	public Employee updateById(@RequestBody Employee employee, @PathVariable("id") String empId)
			throws EmployeeNotFoundException {
		LOGGER.info("Inside updateById of EmployeeController");
		return employeeService.updateById(employee, empId);

	}

	@GetMapping("/employee/{id}")
	public Employee fetchById(@PathVariable("id") String empId) throws EmployeeNotFoundException {
		LOGGER.info("Inside fetchById of EmployeeController");
		return employeeService.fetchById(empId);
	}

	@GetMapping("/employee/{phone}")
	public Employee fetchByPhone(@PathVariable("phone") String phone) throws EmployeeNotFoundException {
		LOGGER.info("Inside fetchByPhone of EmployeeController");
		return employeeService.fetchByPhone(phone);
	}

	@GetMapping("/employee/{email}")
	public Employee fetchByEmail(@PathVariable("email") String email) throws EmployeeNotFoundException {
		LOGGER.info("Inside fetchByEmail of EmployeeController");
		return employeeService.fetchByEmail(email);
	}

	@GetMapping("/employee")
	public List<Employee> fetchListOfEmployee() {
		LOGGER.info("Inside fetchListOfEmployee of EmployeeController");
		return employeeService.fetchListOfEmployee();
	}

	@GetMapping("/empfetchbydate")
	public List<Employee> fetchByCreateDateBetween(@PathVariable LocalDate startDate, @PathVariable LocalDate endDate) {
		LOGGER.info("Inside fetchByCreateDateBetween of EmployeeController");
		return employeeService.fetchByCreateDateBetween(startDate, endDate);

	}

	@DeleteMapping("/employee/{id}")
	public String deleteById(@PathVariable("id") String empId) throws EmployeeNotFoundException {
		LOGGER.info("Inside deleteById of EmployeeController");
		employeeService.deleteById(empId);
		return "Employee Deleted Successfully";

	}

}
