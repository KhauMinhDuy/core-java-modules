package com.khauminhduy.regex;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class RegexUnitTest {

	private static Pattern pattern;
	private static Matcher matcher;

	@Test
	public void givenText_whenSimpleRegexMatches_thenCorrect() {
		Pattern pattern = Pattern.compile("foo");
		Matcher matcher = pattern.matcher("foo");
		assertTrue(matcher.find());
	}

	@Test
	public void givenText_whenSimpleRegexMatchesTwice_thenCorrect() {
		Pattern pattern = Pattern.compile("foo");
		Matcher matcher = pattern.matcher("foofoo");
		int matchers = 0;
		while (matcher.find()) {
			matchers++;
		}

		assertEquals(2, matchers);
	}
	
	@Test
	public void givenText_whenMatchesWithDotMetach_thenCorrect() {
		int matchers = runTest(".", "foo");
		assertTrue(matchers > 0);
	}
	
	@Test
	public void givenRepeatedText_whenMatchesOnceWithDotMetach_thenCorrect() {
		int matchers = runTest("foo.", "foofoo");
		assertTrue(matchers > 0);
		assertEquals(1, matchers);
	}
	
	@Test
	public void givenORSet_whenMatchesAny_thenCorrect() {
		int matchers = runTest("[abc]", "b");
		assertTrue(matchers > 0);
		assertEquals(1, matchers);
	}
	
	@Test
	public void givenORSet_whenMatchesAnyAndAll_thenCorrect() {
		int matchers = runTest("[abc]", "cab");
		assertTrue(matchers > 0);
		assertEquals(3, matchers);
	}
	
	@Test
	public void givenORSet_whenMatchesAllCombinations_thenCorrect() {
		int matchers = runTest("[bcr]at", "bat cat rat");
		assertTrue(matchers > 0);
		assertEquals(3, matchers);
	}
	
	@Test
	public void givenNORSet_whenMatchesNon_thenCorrect() {
		int matchers = runTest("[^abc]", "g");
		assertTrue(matchers > 0);
		assertEquals(1, matchers);
	}
	
	@Test
	public void givenNORSet_whenMatchesAllExceptElements_thenCorrect() {
		int matchers = runTest("[^bcr]at", "sat mat eat");
		assertTrue(matchers > 0);
		assertEquals(3, matchers);
	}
	
	@Test
	public void givenUpperCaseRange_whenMatchesUpperCase_thenCorrect() {
		int matchers = runTest("[A-Z]", "Two Uppercase alphabets 34 overall");
		assertTrue(matchers > 0);
		assertEquals(2, matchers);
	}
	
	@Test
	public void givenLowerCaseRange_whenMatchesLowerCase_thenCorrect() {
		int matchers = runTest("[a-z]", "Two Uppercase alphabets 34 overall");
		assertTrue(matchers > 0);
		assertEquals(26, matchers);
	}
	
	@Test
	public void givenBothLowerAndUpperCaseRange_whenMatchesAllLetters_thenCorrect() {
		 int matchers = runTest("[a-zA-Z]", "Two Uppercase alphabets 34 overall");
		 assertTrue(matchers > 0);
		 assertEquals(28, matchers);
	}
	
	@Test
	public void givenNumberRange_whenMatchesAccurately_thenCorrect() {
		int matchers = runTest("[1-5]", "Two Uppercase alphabets 34 overall");
		assertTrue(matchers > 0);
		assertEquals(2, matchers);
	}
	
	@Test
	public void givenNumberRange_whenMatchesAccurately_thenCorrect2() {
		int matchers = runTest("[30-35]", "Two Uppercase alphabets 34 overall");
		assertTrue(matchers > 0);
		assertEquals(1, matchers);
	}
	
	@Test
	public void givenTwoSets_whenMatchesUnion_thenCorrect() {
		int matchers = runTest("[1-3[7-9]]", "123456789");
		assertTrue(matchers > 0);
		assertEquals(6, matchers);
	}
	
	@Test
	public void givenTwoSets_whenMatchesIntersection_thenCorrect() {
		int matchers = runTest("[1-6&&[3-9]]", "123456789");
		assertTrue(matchers > 0);
		assertEquals(4, matchers);
	}
	
	@Test
	public void givenSetWithSubtraction_whenMatchesAccurately_thenCorrect() {
		int matchers = runTest("[0-9&&[^2468]]", "123456789");
		assertTrue(matchers > 0);
		assertEquals(5, matchers);
	}

	private synchronized static int runTest(String regex, String text) {
		pattern = Pattern.compile(regex);
		matcher = pattern.matcher(text);
		int matchers = 0;
		while (matcher.find()) {
			matchers++;
		}
		return matchers;
	}

	private synchronized static int runTest(String regex, String text, int flags) {
		pattern = Pattern.compile(regex, flags);
		matcher = pattern.matcher(text);
		int matches = 0;
		while (matcher.find())
			matches++;
		return matches;
	}

}
