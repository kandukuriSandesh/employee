package com.example.employee.legacy;

import com.example.employee.entity.LeaveRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class LegacyLeaveReportGenerator implements LegacyReportGenerator {

	@Override
	public List<String> generate(List<LeaveRequest> leaves) {
		List<String> lines = new ArrayList<String>();

		// INTENTIONALLY LEGACY: used for Java 8 to Java 17/21 modernization practice.
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		for (LeaveRequest leave : leaves) {
			lines.add("Leave#" + leave.getId()
				+ " employee=" + leave.getEmployeeId()
				+ " type=" + leave.getLeaveType()
				+ " status=" + leave.getStatus()
				+ " from=" + format.format(leave.getFromDate())
				+ " to=" + format.format(leave.getToDate()));
		}

		return lines;
	}
}
