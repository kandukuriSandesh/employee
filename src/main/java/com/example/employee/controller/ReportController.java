package com.example.employee.controller;

import com.example.employee.service.ReportService;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reports")
public class ReportController {

	private final ReportService reportService;

	public ReportController(ReportService reportService) {
		this.reportService = reportService;
	}

	@GetMapping("/legacy-leaves")
	public List<String> getLegacyLeaveReport() {
		return reportService.getLegacyLeaveReport();
	}

	@GetMapping(value = "/xml", produces = MediaType.APPLICATION_XML_VALUE)
	public String getLegacyXmlReport() {
		return reportService.exportLegacyXml();
	}
}
