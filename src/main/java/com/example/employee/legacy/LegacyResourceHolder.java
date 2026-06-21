package com.example.employee.legacy;

import org.springframework.stereotype.Component;

@Component
public class LegacyResourceHolder {

	private boolean cleaned;

	public boolean isCleaned() {
		return cleaned;
	}

	@Override
	protected void finalize() throws Throwable {
		try {
			// INTENTIONALLY LEGACY: used for Java 8 to Java 17/21 modernization practice.
			// finalize is deprecated and should later be replaced with AutoCloseable.
			cleaned = true;
		} finally {
			super.finalize();
		}
	}
}
