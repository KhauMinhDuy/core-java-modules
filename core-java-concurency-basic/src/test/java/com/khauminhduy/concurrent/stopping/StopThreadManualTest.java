package com.khauminhduy.concurrent.stopping;

import static com.jayway.awaitility.Awaitility.await;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.Test;

import com.jayway.awaitility.Awaitility;
import com.khauminhduy.stopping.ControlSubThread;

public class StopThreadManualTest {

	@Test
	public void whenStoppedThreadIsStopped() throws InterruptedException {
		int interval = 5;
		ControlSubThread controlSubThread = new ControlSubThread(interval);
		controlSubThread.start();
		
		Thread.sleep(interval);
		assertTrue(controlSubThread.isRunning());
		assertFalse(controlSubThread.isStop());
		
		controlSubThread.stop();
		
		await().until(() -> assertTrue(controlSubThread.isStop()));
	}
	
	@Test
	public void whenInterruptedThreadIsStopped() throws InterruptedException {
		int interval = 50;
		
		ControlSubThread controlSubThread = new ControlSubThread(interval);
		controlSubThread.start();
		
		Thread.sleep(interval);
		assertTrue(controlSubThread.isRunning());
		assertFalse(controlSubThread.isStop());
		
		controlSubThread.interrupt();
		
		Awaitility.await().pollDelay(2, TimeUnit.MILLISECONDS)
						.atMost(interval / 10, TimeUnit.MILLISECONDS)
						.until(controlSubThread::isStop);
	}
	
}
