package com.khauminhduy.concurrent.callable;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.khauminhduy.callable.FactorialTask;

public class FactorialTaskManualTest {

	private ExecutorService executorService;

	@Before
	public void init() {
		executorService = Executors.newSingleThreadExecutor();
	}

	@Test
	public void whenTaskSubmitted_ThenFutureResultObtained() throws InterruptedException, ExecutionException {
		FactorialTask factorialTask = new FactorialTask(5);
		Future<Integer> future = executorService.submit(factorialTask);
		assertEquals(120, future.get().intValue());
	}

	@Test(expected = ExecutionException.class)
	public void whenException_ThenCallableThrowsIt() throws InterruptedException, ExecutionException {
		FactorialTask task = new FactorialTask(-5);
		Future<Integer> future = executorService.submit(task);
		future.get().intValue();
	}

	@Test
	public void whenException_ThenCallableDoesntThrowsItIfGetIsNotCalled() {
		FactorialTask task = new FactorialTask(-5);
		Future<Integer> future = executorService.submit(task);
		assertEquals(false, future.isDone());
	}

	@After
	public void cleanup() {
		executorService.shutdown();
	}

}
