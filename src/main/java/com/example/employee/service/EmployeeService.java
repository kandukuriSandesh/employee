package com.example.employee.service;

import com.example.employee.dto.EmployeeRequestDto;
import com.example.employee.entity.Employee;
import com.example.employee.repository.EmployeeRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

	private final EmployeeRepository employeeRepository;

	public EmployeeService(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	public Employee createEmployee(EmployeeRequestDto requestDto) {
		Employee employee = new Employee();
		employee.setName(requestDto.getName());
		employee.setEmail(requestDto.getEmail());
		employee.setDepartment(requestDto.getDepartment());
		employee.setJoiningDate(parseDate(requestDto.getJoiningDate()));
		employee.setCreatedAt(new Date());
		return employeeRepository.save(employee);
	}

	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	public Employee getEmployee(Long id) {
		return employeeRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("Employee not found for id " + id));
	}

	private Date parseDate(String dateValue) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			format.setLenient(false);
			return format.parse(dateValue);
		} catch (ParseException ex) {
			throw new IllegalArgumentException("Invalid date. Expected yyyy-MM-dd", ex);
		}
	}
}
