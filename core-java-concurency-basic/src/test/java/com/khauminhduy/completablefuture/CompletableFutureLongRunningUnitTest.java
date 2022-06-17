package com.khauminhduy.completablefuture;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class CompletableFutureLongRunningUnitTest {

	@Test
	public void whenRunningCompletableFutureAsynchronously_thenGetMethodWaitsForResult()
			throws InterruptedException, ExecutionException {
		Future<String> future = calculateAsync();
		String result = future.get();
		assertEquals("Hello", result);
	}

	@Test
	public void whenRunningCompletableFutureWithResult_thenGetMethodReturnsImmediately()
			throws InterruptedException, ExecutionException {
		CompletableFuture<String> completedFuture = CompletableFuture.completedFuture("Hello");
		String result = completedFuture.get();
		assertEquals("Hello", result);
	}

	@Test(expected = CancellationException.class)
	public void whenCancelingTheFuture_thenThrowsCancellationException() throws InterruptedException, ExecutionException {
		Future<String> future = calculateAsyncWithCancellation();
		future.get();
	}

	@Test
	public void whenCreatingCompletableFutureWithSupplyAsync_thenFutureReturnsValue()
			throws InterruptedException, ExecutionException {
		CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> "Hello");
		assertEquals("Hello", completableFuture.get());
	}

	@Test
	public void whenAddingThenAcceptToFuture_thenFunctionExecutesAfterComputationIsFinished()
			throws InterruptedException, ExecutionException {
		CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> "Hello");
		CompletableFuture<Void> future = completableFuture
				.thenAccept(s -> System.out.println("Computation returned: " + s));
		future.get();
		assertNotNull(future);
	}

	@Test
	public void whenAddingThenRunToFuture_thenFunctionExecutesAfterComputationIsFinished()
			throws InterruptedException, ExecutionException {
		CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> "Hello");
		CompletableFuture<Void> future = completableFuture.thenRun(() -> System.out.println("Computation finished."));
		future.get();
		assertNotNull(future);
	}

	@Test
	public void whenAddingThenApplyToFuture_thenFunctionExecutesAfterComputationIsFinished()
			throws InterruptedException, ExecutionException {
		CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> "Hello");
		CompletableFuture<String> future = completableFuture.thenApply(s -> s + " World");
		String result = future.get();
		assertEquals("Hello World", result);
	}

	@Test
	public void whenUsingThenCompose_thenFuturesExecuteSequentially() throws InterruptedException, ExecutionException {
		CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> "Hello")
				.thenCompose(s -> CompletableFuture.supplyAsync(() -> s + " World"));
		assertEquals("Hello World", completableFuture.get());
	}

	@Test
	public void whenUsingThenCombine_thenWaitForExecutionOfBothFutures() throws InterruptedException, ExecutionException {
		CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> "Hello")
				.thenCombine(CompletableFuture.supplyAsync(() -> " World"), (s1, s2) -> s1 + s2);
		assertEquals("Hello World", completableFuture.get());
	}

	@Test
	public void whenUsingThenAcceptBoth_thenWaitForExecutionOfBothFutures() {
		CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> "Hello")
				.thenAcceptBoth(CompletableFuture.supplyAsync(() -> " World"), (s1, s2) -> System.out.println(s1 + s2));
		assertNotNull(future);
	}

	@Test
	public void whenFutureCombinedWithAllOfCompletes_thenAllFuturesAreDone()
			throws InterruptedException, ExecutionException {
		CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "Hello");
		CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> "Beautiful");
		CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> "World");

		CompletableFuture<Void> combinedFuture = CompletableFuture.allOf(future1, future2, future3);

		combinedFuture.get();

		assertTrue(future1.isDone());
		assertTrue(future2.isDone());
		assertTrue(future3.isDone());

		String result = Stream.of(future1, future2, future3).map(CompletableFuture::join).collect(Collectors.joining(" "));

		assertEquals("Hello Beautiful World", result);
	}

	@Test
	public void whenFutureThrows_thenHandleMethodReceivesException() throws InterruptedException, ExecutionException {
		String name = null;

		// ...

		CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
			if (name == null) {
				throw new RuntimeException("Computation error!");
			}
			return "Hello, " + name;
		}).handle((s, t) -> s != null ? s : "Hello, Stranger");

		assertEquals("Hello, Stranger", completableFuture.get());
	}

	@Test(expected = ExecutionException.class)
	public void whenCompletingFutureExceptionally_thenGetMethodThrows() throws InterruptedException, ExecutionException {
		CompletableFuture<String> completableFuture = new CompletableFuture<>();
		// ...

		completableFuture.completeExceptionally(new RuntimeException("Calculation failed!"));

		// ...

		completableFuture.get();
	}

	@Test
	public void whenAddingThenApplyAsyncToFuture_thenFunctionExecutesAfterComputationIsFinished()
			throws InterruptedException, ExecutionException {
		CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> "Hello");
		CompletableFuture<String> future = completableFuture.thenApplyAsync(s -> s + " World");
		assertEquals("Hello World", future.get());
	}

	@Test
	public void whenPassingTransformation_thenFunctionExecutionWithThenApply()
			throws InterruptedException, ExecutionException {
		CompletableFuture<Integer> completableFuture = compute().thenApply(i -> i + 1);
		assertEquals(new Integer(11), completableFuture.get());
	}

	@Test
	public void whenPassingPreviousStage_thenFunctionExecutionWithThenCompose()
			throws InterruptedException, ExecutionException {
		CompletableFuture<Integer> completableFuture = compute().thenCompose(this::computeAnother);
		assertEquals(new Integer(20), completableFuture.get());
	}

	private Future<String> calculateAsync() {
		CompletableFuture<String> completableFuture = new CompletableFuture<>();

		Executors.newCachedThreadPool().submit(() -> {
			Thread.sleep(300);
			completableFuture.complete("Hello");
			return null;
		});

		return completableFuture;
	}

	private Future<String> calculateAsyncWithCancellation() {
		CompletableFuture<String> completableFuture = new CompletableFuture<>();
		Executors.newCachedThreadPool().submit(() -> {
			Thread.sleep(500);
			completableFuture.cancel(false);
			return null;
		});
		return completableFuture;
	}

	private CompletableFuture<Integer> compute() {
		return CompletableFuture.supplyAsync(() -> 10);
	}

	private CompletableFuture<Integer> computeAnother(Integer i) {
		return CompletableFuture.supplyAsync(() -> 10 + i);
	}

}
