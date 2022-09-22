package com.khauminhduy.mutex;

import com.google.common.util.concurrent.Monitor;

public class SequenceGeneratorUsingMonitor extends SequenceGenerator {

	private Monitor mutex = new Monitor();

	@Override
	public int getNextSequence() {
		try {
			mutex.enter();
			return super.getNextSequence();
		} finally {
			mutex.leave();
		}
			
	}

}
