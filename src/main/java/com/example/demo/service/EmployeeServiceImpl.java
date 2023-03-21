package com.example.demo.service;

import java.time.LocalDate;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Employee;
import com.example.demo.error.EmployeeNotFoundException;
import com.example.demo.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private EmployeeValidation employeeValidation;

	@Override
	public Employee addEmployee(Employee employee) throws EmployeeNotFoundException {
		String st = employeeValidation.validate(employee);
		if (!"".equalsIgnoreCase(st))
			throw new EmployeeNotFoundException(st);
		employee.setEmpCreateDate(LocalDate.now());
		employee.setEmpUpdateDate(LocalDate.now());
		return employeeRepository.save(employee);

	}

	@Override
	public List<Employee> addListOfEmployee(List<Employee> emList) throws EmployeeNotFoundException {
		for (int i = 0; i < emList.size(); i++) {
			String st = employeeValidation.validate(emList.get(i));
			if (!"".equalsIgnoreCase(st))
				throw new EmployeeNotFoundException(st);
			emList.get(i).setEmpCreateDate(LocalDate.now());
			emList.get(i).setEmpUpdateDate(LocalDate.now());
		}
		return employeeRepository.saveAll(emList);
	}

	@Override
	public Employee updateById(Employee employee, String empId) throws EmployeeNotFoundException {
		Employee emp = employeeRepository.findById(empId).get();
		if (emp == null)
			throw new EmployeeNotFoundException("employee  is not avaibale whose ID :- " + empId);
		else {
			String st = employeeValidation.ValidUpdate(employee);
			if (!"".equalsIgnoreCase(st))
				throw new EmployeeNotFoundException(st);
			else {
				Employee emp1 = employeeRepository.findById(empId).get();

				if (Objects.nonNull(employee.getFirstName()) && !"".equalsIgnoreCase(employee.getFirstName())) {
					emp1.setFirstName(employee.getFirstName());
				}
				if (Objects.nonNull(employee.getLastName()) && !"".equalsIgnoreCase(employee.getLastName())) {
					emp1.setLastName(employee.getLastName());
				}
				if (Objects.nonNull(employee.getEmail()) && !"".equalsIgnoreCase(employee.getEmail())) {
					emp1.setEmail(employee.getEmail());
				}
				if (Objects.nonNull(employee.getPhone()) && !"".equalsIgnoreCase(employee.getPhone())) {
					emp1.setPhone(employee.getPhone());
				}
				if (Objects.nonNull(employee.getDateOfBirth())) {
					emp1.setDateOfBirth(employee.getDateOfBirth());
				}
				if (Objects.nonNull(employee.getGender()) && !"".equals(employee.getGender())) {
					emp1.setGender(employee.getGender());
				}
				if (Objects.nonNull(employee.getProfilePhoto()) && !"".equalsIgnoreCase(employee.getProfilePhoto())) {
					emp1.setProfilePhoto(employee.getProfilePhoto());
				}

				emp1.setEmpUpdateDate(LocalDate.now());
				return employeeRepository.save(emp1);
			}
		}
	}


	@Override
	public List<Employee> fetchListOfEmployee() {
		return employeeRepository.findAll();
	}

	@Override
	public List<Employee> fetchByCreateDateBetween(LocalDate startDate, LocalDate endDate) {
		return employeeRepository.findByCreateDateBetween(startDate, endDate);
	}

	@Override
	public void deleteById(String empId) throws EmployeeNotFoundException {
		Employee emp = employeeRepository.findById(empId).get();
		if (emp == null)
			throw new EmployeeNotFoundException("employee  is not avaibale whose ID :- " + empId);
		else
			employeeRepository.deleteById(empId);
	}

	@Override
	public Employee fetchById(String empId) throws EmployeeNotFoundException { 
		Employee emp = employeeRepository.findById(empId).get();
		if (emp == null)
			throw new EmployeeNotFoundException("employee  is not avaibale whose ID :- " + empId);
		return emp;
	}

	@Override
	public Employee fetchByPhone(String phone) throws EmployeeNotFoundException {
		Employee emp= employeeRepository.findByPhone(phone);
		if (emp == null)
			throw new EmployeeNotFoundException("employee  is not avaibale whose phone :- " + phone);
		return emp;
	}

	@Override
	public Employee fetchByEmail(String email) throws EmployeeNotFoundException {
		Employee emp= employeeRepository.findByEmail(email);
		if (emp == null)
			throw new EmployeeNotFoundException("employee  is not avaibale whose email :- " + email);
		return emp;
	}

}
