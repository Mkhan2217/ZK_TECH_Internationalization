package com.example.demo.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.entity.Employee;
import com.example.demo.repository.EmployeeRepository;
@Component
public class EmployeeValidation {
	@Autowired
	private EmployeeRepository employeeRepository;

	public String validate(Employee employee) {
		StringBuilder data = new StringBuilder();
		List<Employee> emps = employeeRepository.findAll();

		// EMPLOYEE ID VALIDATION
		if (Objects.nonNull(employee.getEmpId()) && !"".equalsIgnoreCase(employee.getEmpId())) {
			if (employee.getEmpId().length() <= 36) {
				for (int i = 0; i < emps.size(); i++) {
					if (emps.get(i).getEmpId().equals(employee.getEmpId()))
						data.append("Employee Existed already with id: " + employee.getEmpId() + ",");
				}
			} else
				data.append("Employee id should not exceed 36 characters");
		} else
			data.append("Please enter employeeId");
		// EMPLOYEE FirstName VALIDATION
		if (Objects.nonNull(employee.getFirstName()) && !"".equalsIgnoreCase(employee.getFirstName())) {
			if (!(employee.getFirstName().length() <= 50)) {
				data.append(" max-length of firstname should not exceed 50,");
			}
		} else
			data.append(" Please enter firstName,");
		// EMPLOYEE LastName VALIDATION
		if (Objects.nonNull(employee.getLastName()) && !"".equalsIgnoreCase(employee.getLastName())) {
			if (!(employee.getLastName().length() <= 50))
				data.append(" max-length of lastname should not exceed 50,");
		}
		// EMPLOYEE Gender VALIDATION
		if (Objects.nonNull(employee.getGender()) && !"".equalsIgnoreCase(employee.getGender())) {
			if (employee.getGender().length() == 1) {
				if (!(employee.getGender().matches("(?:[M|F|O])"))) {
					data.append(" Please provide proper gender [M|F|O],");
				}
			} else
				data.append(" max-length of gender should be 1,");

		}
		// EMPLOYEE Email VALIDATION
		if (Objects.nonNull(employee.getEmail()) && !"".equalsIgnoreCase(employee.getEmail())) {
			if ((employee.getEmail().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+[a-zA-Z]{2,}"))) {
				for (int i = 0; i < emps.size(); i++) {
					if (emps.get(i).getEmail().equals(employee.getEmail()))
						data.append(" duplicate email,");
				}
			} else
				data.append(" Email should match with proper format,");

		} else
			data.append(" Please enter email id,");

		// EMPLOYEE Phone VALIDATION
		if (Objects.nonNull(employee.getPhone()) && !"".equalsIgnoreCase(employee.getPhone())) {
			if ((employee.getPhone().matches("[\\\\\\\\\\\\\\\\+]+[0-9]{2}+[-]+[0-9]{10}"))) {
				for (int i = 0; i < emps.size(); i++) {
					if (emps.get(i).getPhone().equals(employee.getPhone()))
						data.append(" duplicate phone number,");
				}
			} else
				data.append(" PhoneNumber should match with proper format,");
		} else {
			data.append(" Please enter phone Number,");
		}
		// EMPLOYEE Password VALIDATION
		if ((Objects.nonNull(employee.getPassword()) && !"".equalsIgnoreCase(employee.getPassword()))) {
			if (((employee.getPassword().length() >= 8 && employee.getPassword().length() <= 20))) {
				if (!(employee.getPassword().matches("^(?=.*?[A-Z])(?=.*?[0-9])(?=.*?[A-Za-z0-9]).{8,16}$"))) {
					data.append(" Password should match with proper format");
				}
			} else {
				data.append(" Password should be in the range of 8-20 characters");
			}
		} else 
			data.append(" Please enter password");
		
		return data.toString();
	}

	public String ValidUpdate(Employee employee) {
		StringBuilder sb = new StringBuilder();
		List<Employee> emps = employeeRepository.findAll();
		// validating firstname
		if (Objects.nonNull(employee.getFirstName()) && !"".equalsIgnoreCase(employee.getFirstName())) {
			if (!(employee.getFirstName().length() <= 50)) {
				sb.append(" max-length of firstname should not exceed 50,");
			}
		}
		// validating lastname
		if (Objects.nonNull(employee.getLastName()) && !"".equalsIgnoreCase(employee.getLastName())) {
			if (!(employee.getLastName().length() <= 50))
				sb.append(" max-length of lastname should not exceed 50,");
		}
		// validating gender
		if (Objects.nonNull(employee.getGender()) && !"".equalsIgnoreCase(employee.getGender())) {
			if (employee.getGender().length() == 1) {
				if (!(employee.getGender().matches("(?:[M|F|O])"))) {
					sb.append(" Please provide proper gender [M|F|O],");
				}
			} else {
				sb.append(" max-length of gender should be 1,");
			}
		}
		// validating email
		if (Objects.nonNull(employee.getEmail()) && !"".equalsIgnoreCase(employee.getEmail())) {
			if ((employee.getEmail().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+[a-zA-Z]{2,}"))) {
				for (int i = 0; i < emps.size(); i++) {
					if (emps.get(i).getEmail().equals(employee.getEmail())) {
						sb.append(" duplicate email,");
					}
				}
			} else {
				sb.append(" Email should match with proper format,");
			}
		}
		// validating phoneNumber
		if (Objects.nonNull(employee.getPhone()) && !"".equalsIgnoreCase(employee.getPhone())) {
			if ((employee.getPhone().matches("^[\\\\\\\\\\\\\\\\+]+[0-9]{2}+[-]+[0-9]{10}"))) {
				for (int i = 0; i < emps.size(); i++) {
					if (emps.get(i).getPhone().equals(employee.getPhone())) {
						sb.append(" duplicate phone number,");
					}
				}
			} else
				sb.append(" PhoneNumber should match with proper format,");
		}
		// validating password
		if ((Objects.nonNull(employee.getPassword()) && !"".equalsIgnoreCase(employee.getPassword()))) {
			if (((employee.getPassword().length() >= 8 && employee.getPassword().length() <= 20))) {
				if (!(employee.getPassword().matches("^(?=.*?[A-Z])(?=.*?[0-9])(?=.*?[A-Za-z0-9]).{8,16}$"))) {
					sb.append(" Password should match with proper format");
				}
			} else {
				sb.append(" Password should be in the range of 8-20 characters");
			}
		}
		return sb.toString();

	}
}
