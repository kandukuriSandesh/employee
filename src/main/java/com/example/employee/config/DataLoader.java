package com.example.employee.config;

import com.example.employee.dto.EmployeeRequestDto;
import com.example.employee.dto.LeaveRequestDto;
import com.example.employee.service.EmployeeService;
import com.example.employee.service.LeaveRequestService;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class DataLoader {

	private final EmployeeService employeeService;
	private final LeaveRequestService leaveRequestService;

	public DataLoader(EmployeeService employeeService, LeaveRequestService leaveRequestService) {
		this.employeeService = employeeService;
		this.leaveRequestService = leaveRequestService;
	}

	// INTENTIONALLY LEGACY: used for Java 8 to Java 17/21 modernization practice.
	@PostConstruct
	public void seedData() {
		if (!employeeService.getAllEmployees().isEmpty()) {
			return;
		}

		employeeService.createEmployee(buildEmployee("Asha Verma", "asha.verma@example.com", "Engineering", "2020-01-15"));
		employeeService.createEmployee(buildEmployee("Rohan Mehta", "rohan.mehta@example.com", "HR", "2019-07-01"));
		employeeService.createEmployee(buildEmployee("Neha Iyer", "neha.iyer@example.com", "Finance", "2021-03-22"));

		List<Long> employeeIds = java.util.Arrays.asList(
			employeeService.getAllEmployees().get(0).getId(),
			employeeService.getAllEmployees().get(1).getId(),
			employeeService.getAllEmployees().get(2).getId()
		);

		leaveRequestService.createLeave(buildLeave(employeeIds.get(0), "ANNUAL", "2024-06-01", "2024-06-03", "Family event"));
		leaveRequestService.createLeave(buildLeave(employeeIds.get(1), "SICK", "2024-07-11", "2024-07-12", "Flu recovery"));
		leaveRequestService.createLeave(buildLeave(employeeIds.get(2), "CASUAL", "2024-08-05", "2024-08-05", "Personal work"));
		leaveRequestService.createLeave(buildLeave(employeeIds.get(0), "ANNUAL", "2024-12-20", "2024-12-24", "Year-end travel"));
	}

	private EmployeeRequestDto buildEmployee(String name, String email, String department, String joiningDate) {
		EmployeeRequestDto dto = new EmployeeRequestDto();
		dto.setName(name);
		dto.setEmail(email);
		dto.setDepartment(department);
		dto.setJoiningDate(joiningDate);
		return dto;
	}

	private LeaveRequestDto buildLeave(Long employeeId, String leaveType, String fromDate, String toDate, String reason) {
		LeaveRequestDto dto = new LeaveRequestDto();
		dto.setEmployeeId(employeeId);
		dto.setLeaveType(leaveType);
		dto.setFromDate(fromDate);
		dto.setToDate(toDate);
		dto.setReason(reason);
		return dto;
	}
}
