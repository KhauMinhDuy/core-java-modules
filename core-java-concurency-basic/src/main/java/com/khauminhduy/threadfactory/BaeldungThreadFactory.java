package com.khauminhduy.threadfactory;

import java.util.concurrent.ThreadFactory;

public class BaeldungThreadFactory implements ThreadFactory {

	private int threadId;
	private String name;
	
	public BaeldungThreadFactory(String name) {
		this.threadId = 1;
		this.name = name;
	}
	
	@Override
	public Thread newThread(Runnable r) {
		Thread thread = new Thread(r, "-Thread_" + threadId);
		System.out.println("created new thread with id : " + threadId + " and name : " + thread.getName());
		threadId++;
    return thread;
	}

}
