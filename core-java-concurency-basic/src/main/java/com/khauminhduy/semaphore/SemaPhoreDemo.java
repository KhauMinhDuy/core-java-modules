package com.khauminhduy.semaphore;

import java.util.concurrent.Semaphore;

public class SemaPhoreDemo {

	private static Semaphore semaphore = new Semaphore(10);
	
	public void execute() throws InterruptedException {

		System.out.println("Available permit : " + semaphore.availablePermits());
		System.out.println("Number of threads waiting to acquire: " + semaphore.getQueueLength());

		if (semaphore.tryAcquire()) {
			try {		
			// perform some critical operations
			} finally {
				semaphore.release();
			}
		}

	}
}
