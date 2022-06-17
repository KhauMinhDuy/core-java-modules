package com.khauminhduy.resourcebundle;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Locale;
import java.util.ResourceBundle;

import org.junit.Test;

public class PropertyResourceUnitTest {

	@Test
	public void givenLocaleUsAsDefualt_whenGetBundleForLocalePlPl_thenItShouldContain3ButtonsAnd1Label() {
		Locale.setDefault(Locale.US);
		ResourceBundle bundle = ResourceBundle.getBundle("resourcebundle.resource", new Locale("pl", "PL"));
		assertTrue(bundle.keySet().containsAll(
				asList("backButton", "helloLabel", "cancelButton", "continueButton", "helloLabelNoEncoding")));
	}

	@Test
	public void givenLocaleUsAsDefualt_whenGetBundleForLocaleFrFr_thenItShouldContainKeys1To3AndKey4() {
		Locale.setDefault(Locale.US);
		ResourceBundle bundle = ResourceBundle.getBundle("resourcebundle.resource", new Locale("fr", "FR"));
		assertTrue(bundle.keySet().containsAll(asList("deleteButton", "helloLabel", "cancelButton", "continueButton")));
	}

	@Test
	public void givenLocaleChinaAsDefualt_whenGetBundleForLocaleFrFr_thenItShouldOnlyContainKeys1To3() {
		Locale.setDefault(Locale.CHINA);
		ResourceBundle bundle = ResourceBundle.getBundle("resourcebundle.resource", new Locale("fr", "FR"));
		assertTrue(bundle.keySet().containsAll(asList("continueButton", "helloLabel", "cancelButton")));
	}

	@Test
	public void givenLocaleChinaAsDefualt_whenGetBundleForLocaleFrFrAndExampleControl_thenItShouldOnlyContainKey5() {
		Locale.setDefault(Locale.CHINA);

		ResourceBundle bundle = ResourceBundle.getBundle("resourcebundle.resource", new Locale("fr", "FR"),
				new ExampleControl());
		assertTrue(bundle.keySet().containsAll(asList("backButton", "helloLabel")));
	}

	@Test
	public void givenValuesDifferentlyEncoded_whenGetBundleForLocalePlPl_thenItShouldContain3ButtonsAnd1Label() {
		ResourceBundle bundle = ResourceBundle.getBundle("resourcebundle.resource", new Locale("pl", "PL"));
		assertEquals("cze\u015b\u0107", bundle.getString("helloLabel"));
//		assertEquals("czeÅ\u009BÄ\u0087", bundle.getString("helloLabelNoEncoding"));
	}

}
