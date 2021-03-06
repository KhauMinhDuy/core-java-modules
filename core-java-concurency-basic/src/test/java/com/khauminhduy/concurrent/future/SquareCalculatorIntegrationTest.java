package com.khauminhduy.concurrent.future;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.junit.Test;

import com.khauminhduy.future.SquareCalculator;

public class SquareCalculatorIntegrationTest {

	private SquareCalculator squareCalculator;

	@Test
	public void givenExecutorIsSingleThreaded_whenTwoExecutionsAreTriggered_thenRunInSequence()
			throws InterruptedException, ExecutionException {
		squareCalculator = new SquareCalculator(Executors.newSingleThreadExecutor());

		Future<Integer> result1 = squareCalculator.calculate(4);
		Future<Integer> result2 = squareCalculator.calculate(1000);

		while (!result1.isDone() || !result2.isDone()) {
			System.out.println(String.format("Task 1 is %s and Task 2 is %s.", result1.isDone() ? "done" : "not done",
					result2.isDone() ? "done" : "not done"));

			Thread.sleep(300);
		}

		System.out.println(String.format("Task 1 is %s and Task 2 is %s.", result1.isDone() ? "done" : "not done",
				result2.isDone() ? "done" : "not done"));

		assertEquals(16, result1.get().intValue());
		assertEquals(1000000, result2.get().intValue());
	}

	@Test(expected = TimeoutException.class)
	public void whenGetWithTimeoutLowerThanExecutionTime_thenThrowException()
			throws InterruptedException, ExecutionException, TimeoutException {
		squareCalculator = new SquareCalculator(Executors.newSingleThreadExecutor());

		Future<Integer> result = squareCalculator.calculate(4);

		result.get(500, TimeUnit.MILLISECONDS);
	}

	@Test
	public void givenExecutorIsMultiThreaded_whenTwoExecutionsAreTriggered_thenRunInParallel()
			throws InterruptedException, ExecutionException {
		squareCalculator = new SquareCalculator(Executors.newFixedThreadPool(2));

		Future<Integer> result1 = squareCalculator.calculate(4);
		Future<Integer> result2 = squareCalculator.calculate(1000);

		while (!result1.isDone() || !result2.isDone()) {
			System.out.println(String.format("Task 1 is %s and Task 2 is %s.", result1.isDone() ? "done" : "not done",
					result2.isDone() ? "done" : "not done"));

			Thread.sleep(300);
		}

		System.out.println(String.format("Task 1 is %s and Task 2 is %s.", result1.isDone() ? "done" : "not done",
				result2.isDone() ? "done" : "not done"));

		assertEquals(16, result1.get().intValue());
		assertEquals(1000000, result2.get().intValue());
	}

}
