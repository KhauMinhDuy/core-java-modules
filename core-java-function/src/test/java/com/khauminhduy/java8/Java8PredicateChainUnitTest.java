package com.khauminhduy.java8;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

public class Java8PredicateChainUnitTest {

	private List<String> names = Arrays.asList("Adam", "Alexander", "John", "Tom");
	
	@Test
	public void whenFilterList_thenSuccess() {
		List<String> result = names.stream()
			.filter(name -> name.startsWith("A"))
			.collect(Collectors.toList());
		
		assertEquals(2, result.size());
		MatcherAssert.assertThat(result, Matchers.contains("Adam", "Alexander"));
	}
	
	@Test
	public void whenFilterListWithMultipleFilters_thenSuccess() {
		List<String> result = names.stream()
			.filter(name -> name.startsWith("A"))
			.filter(name -> name.length() < 5)
			.collect(Collectors.toList());
		
		assertEquals(1, result.size());
		MatcherAssert.assertThat(result, Matchers.contains("Adam"));
	}
	
	@Test
	public void whenFilterListWithComplexPredicate_thenSuccess() {
		List<String> result = names.stream()
			.filter(name -> name.startsWith("A") && name.length() < 5)
			.collect(Collectors.toList());
		
		assertEquals(1, result.size());
		MatcherAssert.assertThat(result, Matchers.contains("Adam"));
	}
	
	@Test
	public void whenFilterListWithCombinedPredicatesInline_thenSuccess() {
		Predicate<String> predicate1 = name -> name.startsWith("A");
		Predicate<String> predicate2 = name -> name.length() < 5;
		List<String> result = names.stream()
			.filter(predicate1.and(predicate2))
			.collect(Collectors.toList());
		
		assertEquals(1, result.size());
		MatcherAssert.assertThat(result, Matchers.contains("Adam"));
	}
	
}
