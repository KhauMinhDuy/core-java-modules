package com.khauminhduy.map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.google.common.collect.ImmutableMap;

public class ImmutableMapUnitTest {

	@Test
	public void whenCollectionsUnModifiableMapMethod_thenOriginalCollectionChangesReflectInUnmodifiableMap() {
		Map<String, String> mutableMap = new HashMap<>();
		mutableMap.put("USA", "North America");

		Map<String, String> unmodifiableMap = Collections.unmodifiableMap(mutableMap);
		assertThrows(UnsupportedOperationException.class, () -> unmodifiableMap.put("Canada", "North America"));

		mutableMap.remove("USA");
		assertFalse(mutableMap.containsKey("USA"));

		mutableMap.put("Mexico", "North America");
		assertTrue(unmodifiableMap.containsKey("Mexico"));
	}

	@Test
	public void whenGuavaImmutableMapFromCopyOfMethod_thenOriginalCollectionChangesDoNotReflectInImmutableMap() {
		Map<String, String> mutableMap = new HashMap<>();
		mutableMap.put("USA", "North America");

		ImmutableMap<String, String> immutableMap = ImmutableMap.copyOf(mutableMap);
		assertTrue(immutableMap.containsKey("USA"));

		assertThrows(UnsupportedOperationException.class, () -> immutableMap.put("Canada", "North America"));

		mutableMap.remove("USA");
		assertTrue(immutableMap.containsKey("USA"));

		mutableMap.put("Mexico", "North America");
		assertFalse(immutableMap.containsKey("Mexico"));
	}

	@Test
	public void whenGuavaImmutableMapFromBuilderMethod_thenOriginalCollectionChangesDoNotReflectInImmutableMap() {
		Map<String, String> mutableMap = new HashMap<>();
		mutableMap.put("USA", "North America");

		ImmutableMap<String, String> immutableMap = ImmutableMap.<String, String>builder().putAll(mutableMap)
				.put("Costa Rica", "North America").build();
		assertTrue(immutableMap.containsKey("USA"));
		assertTrue(immutableMap.containsKey("Costa Rica"));

		assertThrows(UnsupportedOperationException.class, () -> immutableMap.put("Canada", "North America"));

		mutableMap.remove("USA");
		assertTrue(immutableMap.containsKey("USA"));

		mutableMap.put("Mexico", "North America");
		assertFalse(immutableMap.containsKey("Mexico"));

	}

	@Test
	public void whenGuavaImmutableMapFromOfMethod_thenOriginalCollectionChangesDoNotReflectInImmutableMap() {
		ImmutableMap<String, String> immutableMap = ImmutableMap.of("USA", "North America", "Costa Rica", "North America");
		
		assertTrue(immutableMap.containsKey("USA"));
		assertTrue(immutableMap.containsKey("Costa Rica"));

		assertThrows(UnsupportedOperationException.class, () -> immutableMap.put("Canada", "North America"));
	}

}
