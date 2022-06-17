package com.khauminhduy.runnable;

public class EventLoggingTask implements Runnable {

	@Override
	public void run() {
		String message = "Message read from the event queue";
		System.out.println("Message read from event queue is " + message);
	}

}
