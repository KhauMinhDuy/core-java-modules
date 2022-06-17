package com.khauminhduy.concurrent.waitandnotify;

public class NetworkDriver {

	public static void main(String[] args) {
		Data data = new Data();
		Thread sender = new Thread(new Sender(data));
		Thread receive = new Thread(new Receiver(data));
		
		sender.start();
		receive.start();
	}
	
}
