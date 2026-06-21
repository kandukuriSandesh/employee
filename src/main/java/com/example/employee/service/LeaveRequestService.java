package com.example.employee.service;

import com.example.employee.dto.LeaveRequestDto;
import com.example.employee.entity.LeaveRequest;
import com.example.employee.legacy.LegacyLeaveCache;
import com.example.employee.repository.LeaveRequestRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class LeaveRequestService {

	private final LeaveRequestRepository leaveRequestRepository;
	private final EmployeeService employeeService;
	private final LegacyLeaveCache legacyLeaveCache;

	public LeaveRequestService(
		LeaveRequestRepository leaveRequestRepository,
		EmployeeService employeeService,
		LegacyLeaveCache legacyLeaveCache
	) {
		this.leaveRequestRepository = leaveRequestRepository;
		this.employeeService = employeeService;
		this.legacyLeaveCache = legacyLeaveCache;
	}

	public LeaveRequest createLeave(LeaveRequestDto requestDto) {
		employeeService.getEmployee(requestDto.getEmployeeId());

		LeaveRequest leaveRequest = new LeaveRequest();
		leaveRequest.setEmployeeId(requestDto.getEmployeeId());
		leaveRequest.setLeaveType(requestDto.getLeaveType());
		leaveRequest.setFromDate(parseDate(requestDto.getFromDate()));
		leaveRequest.setToDate(parseDate(requestDto.getToDate()));
		leaveRequest.setReason(requestDto.getReason());
		leaveRequest.setStatus("PENDING");
		leaveRequest.setCreatedAt(new Date());

		LeaveRequest saved = leaveRequestRepository.save(leaveRequest);
		legacyLeaveCache.recordLeave(saved.getEmployeeId(), buildAuditMessage(saved, "created"));
		return saved;
	}

	public List<LeaveRequest> getAllLeaves() {
		return leaveRequestRepository.findAll();
	}

	public LeaveRequest approve(Long id) {
		LeaveRequest leaveRequest = getLeave(id);
		leaveRequest.setStatus("APPROVED");
		LeaveRequest saved = leaveRequestRepository.save(leaveRequest);
		legacyLeaveCache.recordLeave(saved.getEmployeeId(), buildAuditMessage(saved, "approved"));
		return saved;
	}

	public LeaveRequest reject(Long id) {
		LeaveRequest leaveRequest = getLeave(id);
		leaveRequest.setStatus("REJECTED");
		LeaveRequest saved = leaveRequestRepository.save(leaveRequest);
		legacyLeaveCache.recordLeave(saved.getEmployeeId(), buildAuditMessage(saved, "rejected"));
		return saved;
	}

	public LeaveRequest getLeave(Long id) {
		return leaveRequestRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("Leave request not found for id " + id));
	}

	private String buildAuditMessage(LeaveRequest leaveRequest, String action) {
		// INTENTIONALLY LEGACY: used for Java 8 to Java 17/21 modernization practice.
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());

		// INTENTIONALLY LEGACY: used for Java 8 to Java 17/21 modernization practice.
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentTime = format.format(calendar.getTime());

		return "Leave " + leaveRequest.getId() + " " + action + " at " + currentTime;
	}

	private Date parseDate(String dateValue) {
		try {
			// INTENTIONALLY LEGACY: used for Java 8 to Java 17/21 modernization practice.
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			format.setLenient(false);
			return format.parse(dateValue);
		} catch (ParseException ex) {
			throw new IllegalArgumentException("Invalid date. Expected yyyy-MM-dd", ex);
		}
	}
}
