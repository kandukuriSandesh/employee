package com.example.employee.dto;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@Data
@XmlRootElement(name = "legacyLeaveReport")
@XmlAccessorType(XmlAccessType.FIELD)
public class LegacyLeaveReportXmlDto {

	@XmlElement
	private String generatedAt;

	@XmlElement(name = "leave")
	private List<LegacyLeaveReportItemDto> leaves = new ArrayList<LegacyLeaveReportItemDto>();
}
