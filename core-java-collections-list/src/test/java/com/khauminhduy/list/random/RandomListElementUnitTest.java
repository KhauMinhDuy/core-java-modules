package com.khauminhduy.list.random;

import static org.junit.Assert.assertNotNull;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.junit.Test;

import com.google.common.collect.Lists;

public class RandomListElementUnitTest {
	
	@Test
	public void givenList_whenRandomIndexChosen_shouldReturnARandomElementUsingRandom() {
		final List<Integer> list = Lists.newArrayList(1,2,3);
		Random random = new Random();
		Integer value = list.get(random.nextInt(list.size()));
		assertNotNull(value);
	}

	@Test
	public void givenList_whenRandomIndexChosen_shouldReturnARandomElementUsingMathRandom() {
		List<Integer> givenList = Lists.newArrayList(1, 2, 3);
		Integer value = givenList.get((int)(Math.random() * givenList.size()));
		assertNotNull(value);
	}

	@Test
	public void givenList_whenNumberElementsChosen_shouldReturnRandomElementsRepeat() {
		Random random = new Random();
		List<String> givenList = Lists.newArrayList("one", "two", "three", "four");
		int numberOfElements = 2;
		for(int i = 0; i < numberOfElements; i++) {
			int randomIndex = random.nextInt(givenList.size());
			String value = givenList.get(randomIndex);
			assertNotNull(value);
		}
	}

	@Test
	public void givenList_whenNumberElementsChosen_shouldReturnRandomElementsNoRepeat() {
		Random rand = new Random();
		List<String> givenList = Lists.newArrayList("one", "two", "three", "four");
		int numberOfElements = 2;
		for(int i = 0; i < numberOfElements; i++) {
			int randomIndex = rand.nextInt(givenList.size());
			String value = givenList.get(randomIndex);
			givenList.remove(randomIndex);
			assertNotNull(value);
		}
	}

	@Test
	public void givenList_whenSeriesLengthChosen_shouldReturnRandomSeries() {
		List<Integer> givenList = Lists.newArrayList(1, 2, 3, 4, 5, 6);
		Collections.shuffle(givenList);
		int randomSeriesLength = 3;
		givenList.subList(0, randomSeriesLength - 1);
	}

}
