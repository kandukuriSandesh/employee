package com.example.employee.legacy;

import org.springframework.stereotype.Component;

@Component
@SuppressWarnings("deprecation")
public class LegacyServiceFactory {

	public LegacyReportGenerator createReportGenerator() {
		try {
			// INTENTIONALLY LEGACY: used for Java 8 to Java 17/21 modernization practice.
			// This should later be replaced with getDeclaredConstructor().newInstance().
			return (LegacyReportGenerator) LegacyLeaveReportGenerator.class.newInstance();
		} catch (InstantiationException ex) {
			throw new IllegalStateException("Unable to instantiate legacy report generator", ex);
		} catch (IllegalAccessException ex) {
			throw new IllegalStateException("Unable to access legacy report generator", ex);
		}
	}
}
