package com.khauminhduy.conversion;

import static com.khauminhduy.conversion.StreamArrayConversion.intStreamToPrimitiveIntArray;
import static com.khauminhduy.conversion.StreamArrayConversion.integerStreamToIntegerArray;
import static com.khauminhduy.conversion.StreamArrayConversion.primitiveIntArrayToStreamUsingArraysStream;
import static com.khauminhduy.conversion.StreamArrayConversion.primitiveIntArrayToStreamUsingStreamOf;
import static com.khauminhduy.conversion.StreamArrayConversion.stringArrayToStreamUsingArraysStream;
import static com.khauminhduy.conversion.StreamArrayConversion.stringArrayToStreamUsingStreamOf;
import static com.khauminhduy.conversion.StreamArrayConversion.stringStreamToStringArrayUsingFunctionalInterface;
import static com.khauminhduy.conversion.StreamArrayConversion.stringStreamToStringArrayUsingLambda;
import static com.khauminhduy.conversion.StreamArrayConversion.stringStreamToStringArrayUsingMethodReference;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.google.common.collect.Iterators;

@RunWith(JUnit4.class)
public class StreamArrayConversionUnitTest {

	private String[] stringArray = new String[] { "baeldung", "convert", "to", "string", "array" };
	private Integer[] integerArray = new Integer[] { 1, 2, 3, 4, 5, 6, 7 };
	private int[] intPrimitiveArray = new int[] { 1, 2, 3, 4, 5, 6, 7 };

	@Test
	public void givenStringStream_thenConvertToStringArrayUsingFunctionalInterface() {
		Stream<String> stringStream = Stream.of("baeldung", "convert", "to", "string", "array");
		assertArrayEquals(stringArray, stringStreamToStringArrayUsingFunctionalInterface(stringStream));
	}

	@Test
	public void givenStringStream_thenConvertToStringArrayUsingMethodReference() {
		Stream<String> stringStream = Stream.of("baeldung", "convert", "to", "string", "array");
		assertArrayEquals(stringArray, stringStreamToStringArrayUsingMethodReference(stringStream));
	}

	@Test
	public void givenStringStream_thenConvertToStringArrayUsingLambda() {
		Stream<String> stringStream = Stream.of("baeldung", "convert", "to", "string", "array");
		assertArrayEquals(stringArray, stringStreamToStringArrayUsingLambda(stringStream));
	}

	@Test
	public void givenIntegerStream_thenConvertToIntegerArray() {
		Stream<Integer> integerStream = Stream.of(1, 2, 3, 4, 5, 6, 7);
		assertArrayEquals(integerArray, integerStreamToIntegerArray(integerStream));
	}

	@Test
	public void givenIntStream_thenConvertToIntegerArray() {
		Stream<Integer> integerStream = IntStream.rangeClosed(1, 7).boxed();
		assertArrayEquals(intPrimitiveArray, intStreamToPrimitiveIntArray(integerStream));
	}

	@Test
	public void givenStringArray_whenConvertedTwoWays_thenConvertedStreamsAreEqual() {
		assertTrue(Iterators.elementsEqual(stringArrayToStreamUsingArraysStream(stringArray).iterator(),
				stringArrayToStreamUsingStreamOf(stringArray).iterator()));
	}
	
	@Test
	public void givenPrimitiveArray_whenConvertedTwoWays_thenConvertedStreamsAreNotEqual() {
		assertFalse(Iterators.elementsEqual(primitiveIntArrayToStreamUsingArraysStream(intPrimitiveArray).iterator(), 
				primitiveIntArrayToStreamUsingStreamOf(intPrimitiveArray).iterator()));
	}

}
