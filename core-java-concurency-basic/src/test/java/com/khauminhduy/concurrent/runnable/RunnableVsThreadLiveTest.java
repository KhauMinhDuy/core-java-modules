package com.khauminhduy.concurrent.runnable;

import static org.junit.Assert.assertNotNull;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.commons.lang3.RandomUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.khauminhduy.runnable.SimpleCallable;
import com.khauminhduy.runnable.SimpleRunnable;
import com.khauminhduy.runnable.SimpleThread;

public class RunnableVsThreadLiveTest {

	private static ExecutorService executorService;

	@BeforeClass
	public static void init() {
		executorService = Executors.newCachedThreadPool();
	}

	@Test
	public void givenARunnable_whenRunIt_thenResult() throws InterruptedException {
		Thread thread = new Thread(new SimpleRunnable("SimpleRunnable executed using Thread"));
		thread.start();
		thread.join();
	}

	@Test
	public void givenARunnable_whenSubmitToES_thenResult() throws InterruptedException, ExecutionException {
		executorService.submit(new SimpleRunnable("SimpleRunnable executed using ExecutorService")).get();
	}

	@Test
	public void givenARunnableLambda_whenSubmitToES_thenResult() throws InterruptedException, ExecutionException {
		executorService.submit(() -> System.out.println("Lambda runnable executed!!!")).get();
	}

	@Test
	public void givenAThread_whenRunIt_thenResult() throws InterruptedException {
		Thread thread = new SimpleThread("SimpleThread executed using Thread");
		thread.start();
		thread.join();
	}

	@Test
	public void givenAThread_whenSubmitToES_thenResult() throws InterruptedException, ExecutionException {
		executorService.submit(new SimpleThread("SimpleThread executed using ExecutorService")).get();
	}

	@Test
	public void givenACallable_whenSubmitToES_thenResult() throws InterruptedException, ExecutionException {
		Future<Integer> future = executorService.submit(new SimpleCallable());
		System.out.println("Result from callable: " + future.get());
		assertNotNull(future);
	}

	@Test
	public void givenACallableAsLambda_whenSubmitToES_thenResult() throws InterruptedException, ExecutionException {
		Future<Integer> future = executorService.submit(() -> RandomUtils.nextInt(0, 100));
		System.out.println("Result from callable: " + future.get());
		assertNotNull(future);
	}

	@AfterClass
	public static void tearDown() {
		if (executorService != null && !executorService.isShutdown()) {
			executorService.shutdown();
		}
	}

}