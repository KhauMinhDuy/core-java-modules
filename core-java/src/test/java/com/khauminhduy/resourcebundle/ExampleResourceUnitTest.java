package com.khauminhduy.resourcebundle;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;

import org.junit.Test;

public class ExampleResourceUnitTest {

	@Test
	public void whenGetBundleExampleResourceForLocalePlPl_thenItShouldInheritPropertiesGreetingAndLanguage() {
		Locale plLocale = new Locale("pl", "PL");
		ResourceBundle bundle = ResourceBundle.getBundle("com.khauminhduy.resourcebundle.ExampleResource", plLocale);
		assertTrue(
				bundle.keySet().containsAll(Arrays.asList("toUsdRate", "cities", "greeting", "currency", "language")));
		assertEquals("cześć", bundle.getString("greeting"));
		assertEquals("polish", bundle.getString("language"));
		assertEquals("polish zloty", bundle.getString("currency"));
		assertEquals(new BigDecimal("3.401"), bundle.getObject("toUsdRate"));
		assertArrayEquals(new String[] { "Warsaw", "Cracow" }, bundle.getStringArray("cities"));
	}

	@Test
	public void whenGetBundleExampleResourceForLocaleUs_thenItShouldContainOnlyGreeting() {
		Locale usLocale = Locale.US;
		ResourceBundle exampleBundle = ResourceBundle.getBundle("com.khauminhduy.resourcebundle.ExampleResource",
				usLocale);
		assertFalse(exampleBundle.keySet().containsAll(Arrays.asList("toUsdRate", "cities", "currency", "language")));
		assertTrue(exampleBundle.keySet().containsAll(Arrays.asList("greeting")));
	}

}
