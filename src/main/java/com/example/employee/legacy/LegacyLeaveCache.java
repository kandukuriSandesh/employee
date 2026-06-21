package com.example.employee.legacy;

import java.util.Hashtable;
import java.util.Vector;
import org.springframework.stereotype.Component;

@Component
public class LegacyLeaveCache {

	private final Hashtable<Long, Integer> leaveCountByEmployee = new Hashtable<Long, Integer>();

	private final Vector<String> auditTrail = new Vector<String>();

	public synchronized void recordLeave(Long employeeId, String message) {
		Integer current = leaveCountByEmployee.get(employeeId);
		if (current == null) {
			current = Integer.valueOf(0);
		}
		leaveCountByEmployee.put(employeeId, Integer.valueOf(current.intValue() + 1));
		auditTrail.add(message);
	}

	public synchronized int getLeaveCount(Long employeeId) {
		Integer count = leaveCountByEmployee.get(employeeId);
		return count == null ? 0 : count.intValue();
	}

	public synchronized Vector<String> getAuditTrailSnapshot() {
		return new Vector<String>(auditTrail);
	}
}
