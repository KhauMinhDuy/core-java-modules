package com.khauminhduy.executorservice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceDemo {

	ExecutorService executor = Executors.newFixedThreadPool(10);

	public void execute() {
		executor.submit(Task::new);

		executor.shutdown();
		executor.shutdownNow();

		try {
			executor.awaitTermination(20L, TimeUnit.NANOSECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
