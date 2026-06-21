package com.example.employee.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EmployeeRequestDto {

	@NotBlank
	private String name;

	// INTENTIONALLY LEGACY: used for Java 8 to Java 17/21 modernization practice.
	@Email
	@NotBlank
	private String email;

	@NotBlank
	private String department;

	@NotBlank
	private String joiningDate;
}
