package com.khauminhduy.stopping;

import java.util.concurrent.atomic.AtomicBoolean;

public class ControlSubThread implements Runnable {

	private Thread worker;
	private int interval = 100;
	private AtomicBoolean running = new AtomicBoolean(false);
	private AtomicBoolean stopped = new AtomicBoolean(true);
	
	public ControlSubThread(int sleepInterval) {
		this.interval = sleepInterval;
	}
	
	public void start() {
		worker = new Thread(this);
		worker.start();
	}

	public void stop() {
		running.set(false);
	}
	
	public void interrupt() {
		running.set(false);
		worker.interrupt();
	}
	
	public boolean isRunning() {
		return running.get();
	}
	
	public boolean isStop() {
		return stopped.get();
	}
	
	@Override
	public void run() {
		running.set(true);
		stopped.set(false);
		while(running.get()) {
			try {
				Thread.sleep(interval);
			} catch (InterruptedException e) {
				e.printStackTrace();
				Thread.currentThread().interrupt();
			}
			
			// do something
		}
		stopped.set(true);
	}
	
	
	
}
