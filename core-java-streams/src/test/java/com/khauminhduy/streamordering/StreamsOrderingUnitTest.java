package com.khauminhduy.streamordering;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Before;
import org.junit.Test;

public class StreamsOrderingUnitTest {

	Logger logger = Logger.getLogger(StreamsOrderingUnitTest.class.getName());

	@Before
	public void setup() {
		logger.setLevel(Level.ALL);
	}

	@Test
	public void givenTwoCollections_whenStreamed_thenCheckOutputDifferent() {
		List<String> list = Arrays.asList("B", "A", "C", "D", "F");
		Set<String> set = new TreeSet<>(Arrays.asList("B", "A", "C", "D", "F"));

		Object[] listOutput = list.stream().toArray();
		Object[] setOutput = set.stream().toArray();

		assertEquals("[B, A, C, D, F]", Arrays.toString(listOutput));
		assertEquals("[A, B, C, D, F]", Arrays.toString(setOutput));
	}

	@Test
	public void givenTwoCollections_whenStreamedInParallel_thenCheckOutputDifferent() {
		List<String> list = Arrays.asList("B", "A", "C", "D", "F");
		Set<String> set = new TreeSet<>(Arrays.asList("B", "A", "C", "D", "F"));

		Object[] listOutput = list.parallelStream().toArray();
		Object[] setOutput = set.parallelStream().toArray();

		assertEquals("[B, A, C, D, F]", Arrays.toString(listOutput));
		assertEquals("[A, B, C, D, F]", Arrays.toString(setOutput));
	}

	@Test
	public void givenOrderedInput_whenUnorderedAndOrderedCompared_thenCheckUnorderedOutputChanges() {
		Set<Integer> set = new TreeSet<>(
				Arrays.asList(-9, -5, -4, -2, 1, 2, 4, 5, 7, 9, 12, 13, 16, 29, 23, 34, 57, 68, 90, 102, 230));

		Object[] orderedArray = set.stream().parallel().limit(5).toArray();
		Object[] unorderedArray = set.stream().unordered().parallel().limit(5).toArray();

		logger.info(Arrays.toString(orderedArray));
		logger.info(Arrays.toString(unorderedArray));
	}

	@Test
	public void givenUnsortedStreamInput_whenStreamSorted_thenCheckOrderChanged() {
		List<Integer> list = Arrays.asList(-3, 10, -4, 1, 3);

		Object[] listOutput = list.stream().toArray();
		Object[] listOutputSorted = list.stream().sorted().toArray();

		assertEquals("[-3, 10, -4, 1, 3]", Arrays.toString(listOutput));
		assertEquals("[-4, -3, 1, 3, 10]", Arrays.toString(listOutputSorted));
	}

}
