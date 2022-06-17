package com.khauminhduy.concurrent.threadsafety;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;

public class CounterUnitTest {

	@Test
	public void whenCalledIncrementCounter_thenCorrect() {
		ExecutorService executorService = Executors.newFixedThreadPool(2);
		
	}
	
}
