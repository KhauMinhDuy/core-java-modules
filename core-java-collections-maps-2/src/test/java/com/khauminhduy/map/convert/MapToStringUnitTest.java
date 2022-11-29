package com.khauminhduy.map.convert;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MapToStringUnitTest {

	private Map<Integer, String> wordsByKey = new HashMap<>();

	@BeforeEach
	public void setup() {
		wordsByKey.clear();
		wordsByKey.put(1, "one");
		wordsByKey.put(2, "two");
		wordsByKey.put(3, "three");
		wordsByKey.put(4, "four");
	}

	@Test
	public void givenMap_WhenUsingIteration_ThenResultingMapIsCorrect() {
		String mapAsString = MapToString.convertWithIteration(wordsByKey);
		System.out.println(mapAsString);
		assertEquals("{1 = one, 2 = two, 3 = three, 4 = four}", mapAsString);
	}

	@Test
	public void givenMap_WhenUsingStream_ThenResultingMapIsCorrect() {
		
	}

}
