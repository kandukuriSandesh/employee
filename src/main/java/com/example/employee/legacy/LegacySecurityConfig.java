package com.example.employee.legacy;

import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
@SuppressWarnings({"deprecation", "removal"})
public class LegacySecurityConfig {

	// INTENTIONALLY LEGACY: used for Java 8 to Java 17/21 modernization practice.
	@PostConstruct
	public void installSecurityManager() {
		try {
			if (Boolean.getBoolean("legacy.security.manager.enabled")) {
				// INTENTIONALLY LEGACY: used for Java 8 to Java 17/21 modernization practice.
				// SecurityManager is deprecated/removed in newer Java versions.
				System.setSecurityManager(new SecurityManager());
			}
		} catch (Throwable ex) {
			// Keep startup safe on newer JDKs where this may warn or fail.
		}
	}
}
