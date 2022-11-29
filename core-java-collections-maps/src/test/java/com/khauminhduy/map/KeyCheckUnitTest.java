package com.khauminhduy.map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Collections;
import java.util.Map;

import org.junit.Test;

public class KeyCheckUnitTest {

	@Test
	public void whenKeyIsPresent_thenContainsKeyReturnsTrue() {
		Map<String, String> map = Collections.singletonMap("Key", "Value");

		assertTrue(map.containsKey("Key"));
		assertFalse(map.containsKey("missing"));
	}

	@Test
	public void whenKeyHasNullValue_thenGetStillWorks() {
		Map<String, String> map = Collections.singletonMap("nothing", null);

		assertTrue(map.containsKey("nothing"));
		assertNull(map.get("nothing"));
	}

}
