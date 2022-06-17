package com.khauminhduy.properties;

import static org.junit.Assert.assertEquals;

import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Stream;

import org.junit.Test;

public class MergePropertiesUnitTest {

	@Test
	public void givenTwoProperties_whenMergedUsingIteration_thenAllPropertiesInResult() {
		Properties globalProperties = mergePropertiesByIteratingKeySet(propertiesA(), propertiesB());
		testMergedProperties(globalProperties);
	}

	@Test
	public void givenTwoProperties_whenMergedUsingPutAll_thenAllPropertiesInResult() {
		Properties globalProperties = mergePropertiesByUsingPutAll(propertiesA(), propertiesB());
		testMergedProperties(globalProperties);
	}

	@Test
	public void givenTwoProperties_whenMergedUsingStreamAPI_thenAllPropertiesInResult() {
		Properties globalProperties = mergePropertiesByUsingStreamApi(propertiesB(), propertiesA());
		testMergedProperties(globalProperties);
	}

	private Properties mergePropertiesByIteratingKeySet(Properties... properties) {
		Properties mergeProperties = new Properties();
		for (Properties property : properties) {
			Set<String> propertyNames = property.stringPropertyNames();
			for (String propertyName : propertyNames) {
				String propertyValue = property.getProperty(propertyName);
				mergeProperties.setProperty(propertyName, propertyValue);
			}
		}
		return mergeProperties;
	}

	public Properties mergePropertiesByUsingPutAll(Properties... properties) {
		Properties mergeProperties = new Properties();
		for (Properties property : properties) {
			mergeProperties.putAll(property);
		}
		return mergeProperties;
	}

	private Properties mergePropertiesByUsingStreamApi(Properties... properties) {
		return Stream.of(properties).collect(Properties::new, Map::putAll, Map::putAll);
	}

	private Properties propertiesA() {
		Properties properties = new Properties();
		properties.setProperty("application.name", "my-app");
		properties.setProperty("application.version", "1.0");
		return properties;
	}

	private Properties propertiesB() {
		Properties properties = new Properties();
		properties.setProperty("property-1", "sample property");
		properties.setProperty("property-2", "another sample property");
		return properties;
	}

	private void testMergedProperties(Properties globalProperties) {
		assertEquals("There should be 4 properties", 4, globalProperties.size());
		assertEquals("Property should be", "my-app", globalProperties.getProperty("application.name"));
		assertEquals("Property should be", "1.0", globalProperties.getProperty("application.version"));
		assertEquals("Property should be", "sample property", globalProperties.getProperty("property-1"));
		assertEquals("Property should be", "another sample property", globalProperties.getProperty("property-2"));
	}

}
