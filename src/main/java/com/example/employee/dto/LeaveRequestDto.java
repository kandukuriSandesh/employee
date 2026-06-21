package com.example.employee.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LeaveRequestDto {

	@NotNull
	private Long employeeId;

	@NotBlank
	private String leaveType;

	@NotBlank
	private String fromDate;

	@NotBlank
	private String toDate;

	@NotBlank
	private String reason;
}
