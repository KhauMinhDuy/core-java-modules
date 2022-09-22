package com.khauminhduy.mutex;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class MutexUnitTest {
	
	@Test
	public void givenUnsafeSequenceGenerator_whenRaceCondition_thenUnexpectedBehavior() throws InterruptedException, ExecutionException {
		int count = 1000;
		Set<Integer> uniqueSequences = getUniqueSequences(new SequenceGenerator(), count);
		assertNotEquals(count, uniqueSequences.size());
	}

	@Test
	public void givenSequenceGeneratorUsingSynchronizedMethod_whenRaceCondition_thenSuccess() throws InterruptedException, ExecutionException {
		int count = 1000;
		Set<Integer> uniqueSequences = getUniqueSequences(new SequenceGeneratorUsingSynchronizedMethod(), count);
		assertEquals(count, uniqueSequences.size());
	}

	@Test
	public void givenSequenceGeneratorUsingSynchronizedBlock_whenRaceCondition_thenSuccess() throws InterruptedException, ExecutionException {
		int count = 1000;
		Set<Integer> uniqueSequences = getUniqueSequences(new SequenceGeneratorUsingSynchronizedBlock(), count);
		assertEquals(count, uniqueSequences.size());
	}

	@Test
	public void givenSequenceGeneratorUsingReentrantLock_whenRaceCondition_thenSuccess() throws InterruptedException, ExecutionException {
		int count = 1000;
		Set<Integer> uniqueSequences = getUniqueSequences(new SequenceGeneratorUsingReentrantLock(), count);
		assertEquals(count, uniqueSequences.size());	
	}

	@Test
	public void givenSequenceGeneratorUsingSemaphore_whenRaceCondition_thenSuccess() throws InterruptedException, ExecutionException {
		int count = 1000;
		Set<Integer> uniqueSequences = getUniqueSequences(new SequenceGeneratorUsingSemaphore(), count);
		assertEquals(count, uniqueSequences.size());
	}

	@Test
	public void givenSequenceGeneratorUsingMonitor_whenRaceCondition_thenSuccess() throws InterruptedException, ExecutionException {
		int count = 1000;
		Set<Integer> uniqueSequences = getUniqueSequences(new SequenceGeneratorUsingMonitor(), count);
		assertEquals(count, uniqueSequences.size());
	}

	private Set<Integer> getUniqueSequences(SequenceGenerator sequenceGenerator, int count) throws InterruptedException, ExecutionException {
		ExecutorService executorService = Executors.newFixedThreadPool(3);
		Set<Integer> uniqueSequences = new LinkedHashSet<>();
		List<Future<Integer>> futures = new ArrayList<>();

		for(int i = 0; i < count; i++) {
			futures.add(executorService.submit(sequenceGenerator::getNextSequence));
		}

		for(Future<Integer> future : futures) {
			uniqueSequences.add(future.get());
		}

		executorService.awaitTermination(1, TimeUnit.SECONDS);
		executorService.shutdown();

		return uniqueSequences;
		
	}

}
