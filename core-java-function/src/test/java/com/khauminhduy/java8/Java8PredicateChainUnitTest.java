package com.khauminhduy.java8;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import org.junit.Test;

public class Java8PredicateChainUnitTest {

	private List<String> names = Arrays.asList("Adam", "Alexander", "John", "Tom");
	
	@Test
	public void whenFilterList_thenSuccess() {
		List<String> result = names.stream()
			.filter(name -> name.startsWith("A"))
			.collect(toList());
		
		assertEquals(2, result.size());
		assertThat(result, contains("Adam", "Alexander"));
	}
	
	@Test
	public void whenFilterListWithMultipleFilters_thenSuccess() {
		List<String> result = names.stream()
			.filter(name -> name.startsWith("A"))
			.filter(name -> name.length() < 5)
			.collect(toList());
		
		assertEquals(1, result.size());
		assertThat(result, contains("Adam"));
	}
	
	@Test
	public void whenFilterListWithComplexPredicate_thenSuccess() {
		List<String> result = names.stream()
			.filter(name -> name.startsWith("A") && name.length() < 5)
			.collect(toList());
		
		assertEquals(1, result.size());
		assertThat(result, contains("Adam"));
	}
	
	@Test
	public void whenFilterListWithCombinedPredicatesInline_thenSuccess() {
		List<String> result = names.stream()
      .filter(((Predicate<String>) name -> name.startsWith("A")).and(name -> name.length() < 5))
      .collect(toList());
    
    assertEquals(1, result.size());
    assertThat(result, contains("Adam"));
	}
	
	@Test
	public void whenFilterListWithCombinedPredicatesUsingAnd_thenSuccess() {
		Predicate<String> predicate1 = str -> str.startsWith("A");
    Predicate<String> predicate2 = str -> str.length() < 5;

    List<String> result = names.stream()
      .filter(predicate1.and(predicate2))
      .collect(toList());

    assertEquals(1, result.size());
    assertThat(result, contains("Adam"));
	}
	
	@Test
	public void whenFilterListWithCombinedPredicatesUsingOr_thenSuccess() {
		Predicate<String> predicate1 = name -> name.startsWith("J");
		Predicate<String> predicate2 = name -> name.length() < 4;
		
		List<String> result = names.stream()
			.filter(predicate1.or(predicate2))
			.collect(toList());
		
		assertEquals(2, result.size());
		assertThat(result, contains("John", "Tom"));
	}
	
	@Test
	public void whenFilterListWithCombinedPredicatesUsingOrAndNegate_thenSuccess() {
		Predicate<String> predicate1 = name -> name.startsWith("J");
		Predicate<String> predicate2 = name -> name.length() < 4;
		
		List<String> result = names.stream()
				.filter(predicate1.or(predicate2.negate()))
				.collect(toList());
			
			assertEquals(3, result.size());
			assertThat(result, contains( "Adam", "Alexander", "John"));
	}
	
	@Test
	public void whenFilterListWithCollectionOfPredicatesUsingAnd_thenSuccess() {
		List<Predicate<String>> allPredicates = new ArrayList<>();
		allPredicates.add(name -> name.startsWith("A"));
		allPredicates.add(name -> name.contains("d"));
		allPredicates.add(name -> name.length() > 4);
		
		List<String> result = names.stream()
			.filter(allPredicates.stream().reduce(name -> true, Predicate::and))
			.collect(toList());
		
		assertEquals(1, result.size());
		assertThat(result, contains("Alexander"));
	}
	
	@Test
	public void whenFilterListWithCollectionOfPredicatesUsingOr_thenSuccess() {
		List<Predicate<String>> allPredicates = new ArrayList<>();
		allPredicates.add(name -> name.startsWith("A"));
		allPredicates.add(name -> name.contains("d"));
		allPredicates.add(name -> name.length() > 4);
		
		List<String> result = names.stream()
        .filter(allPredicates.stream().reduce(x -> false, Predicate::or))
        .collect(toList());

    assertEquals(2, result.size());
    assertThat(result, contains("Adam", "Alexander"));
	}
	
}
