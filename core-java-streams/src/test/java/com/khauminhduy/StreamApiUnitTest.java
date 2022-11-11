package com.khauminhduy;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class StreamApiUnitTest {
	
	@Test
	public void givenList_whenGetLastElementUsingReduce_thenReturnLastElement() {
		List<String> valueList = Arrays.asList("Joe", "John", "Sean");
		String lastElement = StreamApi.getLastElementUsingReduce(valueList);
		assertEquals("Sean", lastElement);
	}

	@Test
	public void givenInfiniteStream_whenGetInfiniteStreamLastElementUsingReduce_thenReturnLastElement() {
		Integer lastElement = StreamApi.getInfiniteStreamLastElementUsingReduce();
		assertEquals(19, lastElement.intValue());
	}

	@Test
	public void givenListAndCount_whenGetLastElementUsingSkip_thenReturnLastElement() {
		List<String> valueList = Arrays.asList("Joe", "John", "Sean");
		String lastElement = StreamApi.getLastElementUsingSkip(valueList);
		assertEquals("Sean", lastElement);
	}

}
