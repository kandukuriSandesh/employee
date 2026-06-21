package com.example.employee.legacy;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.springframework.stereotype.Component;

@Component
@SuppressWarnings({"deprecation", "removal"})
public class LegacyLeaveMonitor {

	private Thread monitorThread;

	// INTENTIONALLY LEGACY: used for Java 8 to Java 17/21 modernization practice.
	@PostConstruct
	public void startMonitor() {
		monitorThread = new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(15000L);
					} catch (InterruptedException ex) {
						Thread.currentThread().interrupt();
						break;
					}
				}
			}
		}, "legacy-leave-monitor");
		monitorThread.setDaemon(true);
		monitorThread.start();
	}

	public void pauseMonitor() {
		// INTENTIONALLY LEGACY: used for Java 8 to Java 17/21 modernization practice.
		monitorThread.suspend();
	}

	public void resumeMonitor() {
		// INTENTIONALLY LEGACY: used for Java 8 to Java 17/21 modernization practice.
		monitorThread.resume();
	}

	public void stopMonitor() {
		// INTENTIONALLY LEGACY: used for Java 8 to Java 17/21 modernization practice.
		monitorThread.stop();
	}

	@PreDestroy
	public void shutdown() {
		if (monitorThread != null && monitorThread.isAlive()) {
			stopMonitor();
		}
	}
}
