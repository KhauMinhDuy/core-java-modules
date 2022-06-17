package com.khauminhduy.hashtable;

import static org.hamcrest.Matchers.hasItems;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ConcurrentModificationException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.function.BiFunction;

import org.hamcrest.MatcherAssert;
import org.junit.Test;

public class HashtableUnitTest {

	@Test
	public void whenPutAndGet_thenReturnsValue() {
		Hashtable<Word, String> hashtable = new Hashtable<>();

		Word word = new Word("cat");
		hashtable.put(word, "an animal");

		String definition = hashtable.get(word);

		assertEquals("an animal", definition);

		definition = hashtable.remove(word);

		assertEquals("an animal", definition);

	}

	@Test
	public void whenThesameInstanceOfKey_thenReturnsValue() {
		Hashtable<Word, String> hashtable = new Hashtable<>();
		Word word = new Word("cat");
		hashtable.put(word, "an animal");
		String extracted = hashtable.get(word);
		assertEquals("an animal", extracted);
	}

	@Test
	public void whenEqualsOverridden_thenReturnsValue() {
		Hashtable<Word, String> hashtable = new Hashtable<>();
		Word word = new Word("cat");
		hashtable.put(word, "an animal");
		String extracted = hashtable.get(new Word("cat"));
		assertEquals("an animal", extracted);
	}

	@Test(expected = NullPointerException.class)
	public void whenNullKey_thenException() {
		Hashtable<Word, String> hashtable = new Hashtable<>();
		hashtable.put(null, "an animal");
	}

	@Test(expected = ConcurrentModificationException.class)
	public void whenIterate_thenFailFast() {
		Hashtable<Word, String> hashtable = new Hashtable<>();
		hashtable.put(new Word("cat"), "an animal");
		hashtable.put(new Word("dog"), "another animal");
		Iterator<Word> iterator = hashtable.keySet().iterator();
		System.out.println("Iterator created");
		hashtable.remove(new Word("dog"));
		System.out.println("element remove");

		while (iterator.hasNext()) {
			Word word = iterator.next();
			System.out.println(hashtable.get(word));
		}
	}

	@Test
	public void whenEnumerate_thenNotFailFast() {
		Hashtable<Word, String> table = new Hashtable<>();
		table.put(new Word("1"), "one");
		table.put(new Word("2"), "two");
		table.put(new Word("3"), "three");
		table.put(new Word("4"), "four");
		table.put(new Word("5"), "five");
		table.put(new Word("6"), "six");
		table.put(new Word("7"), "seven");
		table.put(new Word("8"), "eight");

		Enumeration<Word> enumKey = table.keys();
		System.out.println("Enumeration created");
		table.remove(new Word("1"));
		System.out.println("Element remove");
		while (enumKey.hasMoreElements()) {
			Word word = enumKey.nextElement();
			System.out.println(table.get(word));
		}

		assertTrue(true);
	}

	@Test
	public void whenAddElements_thenIterationOrderUnpredicable() {
		Hashtable<Word, String> table = new Hashtable<>();
		table.put(new Word("1"), "one");
		table.put(new Word("2"), "two");
		table.put(new Word("3"), "three");
		table.put(new Word("4"), "four");
		table.put(new Word("5"), "five");
		table.put(new Word("6"), "six");
		table.put(new Word("7"), "seven");
		table.put(new Word("8"), "eight");

		Iterator<Entry<Word, String>> iterator = table.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<Word, String> entry = iterator.next();
			System.out.println(entry.getValue());
		}
	}

	@Test
	public void whenGetOrDefault_thenDefaultGot() {
		Hashtable<Word, String> table = new Hashtable<>();
		table.put(new Word("cat"), "a small domesticated carnivorous mammal");
		Word word = new Word("dog");
		String definition;

		// old way
		/*
		 * if (table.containsKey(key)) { definition = table.get(key); } else {
		 * definition = "not found"; }
		 */

		// new way
		definition = table.getOrDefault(word, "not found");

		assertEquals("not found", definition);
	}

	@Test
	public void whenPutifAbsent_thenNotRewritten() {
		Hashtable<Word, String> table = new Hashtable<>();
		table.put(new Word("cat"), "a small domesticated carnivorous mammal");

		String definition = "an animal";
		// old way
		/*
		 * if (!table.containsKey(new Word("cat"))) { table.put(new Word("cat"),
		 * definition); }
		 */
		// new way
		table.putIfAbsent(new Word("cat"), definition);

		assertEquals("a small domesticated carnivorous mammal", table.get(new Word("cat")));
	}

	@Test
	public void whenRemovePair_thenCheckKeyAndValue() {
		Hashtable<Word, String> table = new Hashtable<>();
		table.put(new Word("cat"), "a small domesticated carnivorous mammal");

		// old way
		/*
		 * if (table.get(new Word("cat")).equals("an animal")) { table.remove(new
		 * Word("cat")); }
		 */

		// new way
		boolean result = table.remove(new Word("cat"), "an animal");
		assertFalse(result);
	}

	@Test
	public void whenReplacePair_thenValueChecked() {
		Hashtable<Word, String> table = new Hashtable<>();
		table.put(new Word("cat"), "a small domesticated carnivorous mammal");

		String definition = "an animal";

		// old way
		/*
		 * if (table.containsKey(new Word("cat")) && table.get(new
		 * Word("cat")).equals("a small domesticated carnivorous mammal")) {
		 * table.put(new Word("cat"), definition); }
		 */
		// new way
		table.replace(new Word("cat"), "a small domesticated carnivorous mammal", definition);

		assertEquals("an animal", table.get(new Word("cat")));
	}

	@Test
	public void whenKeyIsAbsent_thenNotRewritten() {
		Hashtable<Word, String> table = new Hashtable<>();
		table.put(new Word("cat"), "a small domesticated carnivorous mammal");

		// old way
		/*
		 * if (!table.containsKey(cat)) { String definition = "an animal";// calculate
		 * table.put(new Word("cat"), definition); }
		 */
		// new way
		table.computeIfAbsent(new Word("cat"), key -> "an animal");
		assertEquals("a small domesticated carnivorous mammal", table.get(new Word("cat")));
	}

	@Test
	public void whenKeyIsPresent_thenComputeIfPresent() {
		Hashtable<Word, String> table = new Hashtable<>();
		table.put(new Word("cat"), "a small domesticated carnivorous mammal");

		Word cat = new Word("cat");
		// old way
		/*
		 * if (table.containsKey(cat)) { String concatination = cat.getName() + " - " +
		 * table.get(cat); table.put(cat, concatination); }
		 */

		// new way
		table.computeIfPresent(cat, (key, value) -> key.getName() + " - " + value);
		assertEquals("cat - a small domesticated carnivorous mammal", table.get(new Word("cat")));
	}

	@Test
	public void whenCompute_thenForAllKeys() {
		Hashtable<String, Integer> table = new Hashtable<>();
		String[] animals = { "cat", "dog", "dog", "cat", "bird", "mouse", "mouse" };
		for (String animal : animals) {
			table.compute(animal, (key, value) -> value == null ? 1 : value + 1);
		}
		MatcherAssert.assertThat(table.values(), hasItems(2, 2, 2, 1));
	}

	@Test
	public void whenInsteadOfCompute_thenMerge() {
		Hashtable<String, Integer> table = new Hashtable<String, Integer>();
		String[] animals = { "cat", "dog", "dog", "cat", "bird", "mouse", "mouse" };
		for (String animal : animals) {
			table.merge(animal, 1, (oldValue, value) -> oldValue + value);
		}
		MatcherAssert.assertThat(table.values(), hasItems(2, 2, 2, 1));
	}

	@Test
	public void whenForeach_thenIterate() {
		Hashtable<Word, String> table = new Hashtable<>();
		table.put(new Word("cat"), "a small domesticated carnivorous mammal");
		table.put(new Word("dog"), "another animal");
		table.forEach((key, value) -> System.out.println(key.getName() + " - " + value));
	}

	@Test
	public void whenReplaceall_thenNoIterationNeeded() {
		Hashtable<Word, String> table = new Hashtable<>();
		table.put(new Word("cat"), "a small domesticated carnivorous mammal");
		table.put(new Word("dog"), "another animal");
		table.replaceAll((key, value) -> key.getName() + " - " + value);
		MatcherAssert.assertThat(table.values(),
				hasItems("cat - a small domesticated carnivorous mammal", "dog - another animal"));
	}
}
