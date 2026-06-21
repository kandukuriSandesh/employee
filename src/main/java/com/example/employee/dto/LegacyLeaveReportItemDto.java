package com.example.employee.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class LegacyLeaveReportItemDto {

	@XmlElement
	private Long employeeId;

	@XmlElement
	private String employeeName;

	@XmlElement
	private String leaveType;

	@XmlElement
	private String status;

	@XmlElement
	private String fromDate;

	@XmlElement
	private String toDate;
}
