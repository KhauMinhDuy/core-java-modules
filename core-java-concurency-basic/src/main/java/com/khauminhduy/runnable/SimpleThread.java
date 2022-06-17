package com.khauminhduy.runnable;

public class SimpleThread extends Thread {

	private String message;
	
	public SimpleThread(String message) {
		this.message = message;
	}
	
	@Override
	public void run() {
		System.out.println(message);
	}
	
}
