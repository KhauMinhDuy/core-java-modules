package com.khauminhduy.java8;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.function.Consumer;

import org.junit.Test;

public class Java8ForEachUnitTest {

	@Test
	public void compareForEachMethods_thenPrintResults() {
		List<String> names = new ArrayList<>();
		names.add("Larry");
		names.add("Steve");
		names.add("James");
		names.add("Conan");
		names.add("Ellen");

		// Java 5 - for-loop
		System.out.println("--- Enhanced for-loop ---");
		for (String name : names) {
			System.out.println(name);
		}

		System.out.println("--- Java 8 - forEach ---");
		names.forEach(name -> System.out.println(name));

		System.out.println("--- Print Consumer ---");
		Consumer<String> printConsumer = new Consumer<String>() {

			@Override
			public void accept(String name) {
				System.out.println(name);
			}
		};
		names.forEach(printConsumer);

		System.out.println("--- Anonymous inner class ---");
		names.forEach(new Consumer<String>() {

			@Override
			public void accept(String name) {
				System.out.println(name);
			}
		});

		System.out.println("--- forEach method ---");
		names.forEach(e -> System.out.println(e));

		System.out.println("--- Method Reference ---");
		names.forEach(System.out::println);

		assertEquals(5, names.size());
	}

	@Test
	public void givenList_thenIterateAndPrintResults() {
		List<String> names = Arrays.asList("Larry", "Steve", "James");

		names.forEach(System.out::println);
		assertEquals(3, names.size());
	}

	@Test
	public void givenSet_thenIterateAndPrintResults() {
		Set<String> uniqueNames = new HashSet<>(Arrays.asList("Larry", "Steve", "James"));

		uniqueNames.forEach(System.out::println);

		assertEquals(3, uniqueNames.size());
	}

	@Test
	public void givenQueue_thenIterateAndPrintResults() {
		Queue<String> namesQueue = new ArrayDeque<>(Arrays.asList("Larry", "Steve", "James"));

		namesQueue.forEach(System.out::println);

		assertEquals(3, namesQueue.size());
	}

	@Test
	public void givenMap_thenIterateAndPrintResults() {
		Map<Integer, String> namesMap = new HashMap<>();
		namesMap.put(1, "Larry");
		namesMap.put(2, "Steve");
		namesMap.put(3, "James");
		namesMap.entrySet().forEach(entry -> System.out.println(entry.getKey() + " " + entry.getValue()));
		assertSame(3, namesMap.size());
	}
	
	@Test
	public void givenMap_whenUsingBiConsumer_thenIterateAndPrintResults2() {
		Map<Integer, String> namesMap = new HashMap<>();
    namesMap.put(1, "Larry");
    namesMap.put(2, "Steve");
    namesMap.put(3, "James");
    namesMap.forEach((key, value) -> System.out.println(key + " " + value));
    assertEquals(3, namesMap.size());
	}

}
