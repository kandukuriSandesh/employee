package com.example.employee.legacy;

import com.example.employee.entity.LeaveRequest;
import java.util.List;

public interface LegacyReportGenerator {

	List<String> generate(List<LeaveRequest> leaves);
}
