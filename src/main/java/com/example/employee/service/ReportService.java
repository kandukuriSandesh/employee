package com.example.employee.service;

import com.example.employee.dto.LegacyLeaveReportItemDto;
import com.example.employee.dto.LegacyLeaveReportXmlDto;
import com.example.employee.entity.Employee;
import com.example.employee.entity.LeaveRequest;
import com.example.employee.legacy.LegacyLeaveCache;
import com.example.employee.legacy.LegacyReportGenerator;
import com.example.employee.legacy.LegacyServiceFactory;
import com.example.employee.repository.LeaveRequestRepository;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import org.springframework.stereotype.Service;

@Service
public class ReportService {

	private final LeaveRequestRepository leaveRequestRepository;
	private final EmployeeService employeeService;
	private final LegacyServiceFactory legacyServiceFactory;
	private final LegacyLeaveCache legacyLeaveCache;

	public ReportService(
		LeaveRequestRepository leaveRequestRepository,
		EmployeeService employeeService,
		LegacyServiceFactory legacyServiceFactory,
		LegacyLeaveCache legacyLeaveCache
	) {
		this.leaveRequestRepository = leaveRequestRepository;
		this.employeeService = employeeService;
		this.legacyServiceFactory = legacyServiceFactory;
		this.legacyLeaveCache = legacyLeaveCache;
	}

	public List<String> getLegacyLeaveReport() {
		LegacyReportGenerator reportGenerator = legacyServiceFactory.createReportGenerator();
		List<String> reportLines = new ArrayList<String>(reportGenerator.generate(leaveRequestRepository.findAll()));
		reportLines.addAll(legacyLeaveCache.getAuditTrailSnapshot());
		return reportLines;
	}

	public String exportLegacyXml() {
		try {
			LegacyLeaveReportXmlDto xmlDto = new LegacyLeaveReportXmlDto();

			// INTENTIONALLY LEGACY: used for Java 8 to Java 17/21 modernization practice.
			Calendar calendar = Calendar.getInstance();

			// INTENTIONALLY LEGACY: used for Java 8 to Java 17/21 modernization practice.
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			xmlDto.setGeneratedAt(format.format(calendar.getTime()));

			for (LeaveRequest leaveRequest : leaveRequestRepository.findAll()) {
				Employee employee = employeeService.getEmployee(leaveRequest.getEmployeeId());
				LegacyLeaveReportItemDto itemDto = new LegacyLeaveReportItemDto();
				itemDto.setEmployeeId(employee.getId());
				itemDto.setEmployeeName(employee.getName());
				itemDto.setLeaveType(leaveRequest.getLeaveType());
				itemDto.setStatus(leaveRequest.getStatus());
				itemDto.setFromDate(format.format(leaveRequest.getFromDate()));
				itemDto.setToDate(format.format(leaveRequest.getToDate()));
				xmlDto.getLeaves().add(itemDto);
			}

			// INTENTIONALLY LEGACY: used for Java 8 to Java 17/21 modernization practice.
			JAXBContext jaxbContext = JAXBContext.newInstance(LegacyLeaveReportXmlDto.class);
			// INTENTIONALLY LEGACY: used for Java 8 to Java 17/21 modernization practice.
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

			StringWriter writer = new StringWriter();
			marshaller.marshal(xmlDto, writer);
			return writer.toString();
		} catch (JAXBException ex) {
			throw new IllegalStateException("Unable to create XML report", ex);
		}
	}
}
