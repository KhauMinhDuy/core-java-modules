package com.khauminhduy.collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.PredicateUtils;
import org.hamcrest.Matchers;
import org.junit.Test;

import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public class JavaCollectionCleanupUnitTest {
	
	@Test
	public void givenListContainsNulls_whenRemovingNullsWithPlainJava_thenCorrect() {
		final List<Integer> list = Lists.newArrayList(null, 1, null);
		while(list.remove(null));
		assertThat(list	, hasSize(1));
	}

	@Test
	public void givenListContainsNulls_whenRemovingNullsWithPlainJavaAlternative_thenCorrect() {
		final List<Integer> list = Lists.newArrayList(null, 1, null);
		list.removeAll(Collections.singleton(null));
		assertThat(list, hasSize(1));		
	}

	@Test
	public void givenListContainsNulls_whenRemovingNullsWithGuavaV1_thenCorrect() {
		final List<Integer> list = Lists.newArrayList(null, 1, null);
		Iterables.removeIf(list, Predicates.isNull());
		assertThat(list, hasSize(1));
	}

	@Test
	public void givenListContainsNulls_whenRemovingNullsWithGuavaV2_thenCorrect() {
		final List<Integer> list = Lists.newArrayList(null, 1, null, 2, 3);
		final List<Integer> listWithoutNulls = Lists.newArrayList(Iterables.filter(list, Predicates.notNull()));
		assertThat(listWithoutNulls, hasSize(3));
	}

	@Test
	public void givenListContainsNulls_whenRemovingNullsWithCommonsCollections_thenCorrect() {
		final List<Integer> list = Lists.newArrayList(null, 1, 2, null, 3, null);
		CollectionUtils.filter(list, PredicateUtils.notNullPredicate());
		assertThat(list, hasSize(3));
	}

	@Test
	public void givenListContainsDuplicates_whenRemovingDuplicatesWithPlainJava_thenCorrect() {
		final List<Integer> listWithDuplicates = Lists.newArrayList(5, 0, 3, 1, 2, 3, 0, 0);
		final List<Integer> listWithoutDuplicates = new ArrayList<>(new HashSet<>(listWithDuplicates));
		assertThat(listWithDuplicates, hasSize(8));
		assertThat(listWithoutDuplicates, hasSize(5));
		assertThat(listWithoutDuplicates, Matchers.containsInAnyOrder(5, 0, 3, 1, 2));
	}

	@Test
	public void givenListContainsDuplicates_whenRemovingDuplicatesPreservingOrderWithPlainJava_thenCorrect() {
		final List<Integer> listWithDuplicates = Lists.newArrayList(5, 0, 3, 1, 2, 3, 0, 0);
		final List<Integer> listWithoutDuplicates = new ArrayList<>(new LinkedHashSet<>(listWithDuplicates));
		assertThat(listWithDuplicates, hasSize(8));
		assertThat(listWithoutDuplicates, hasSize(5));
		assertThat(listWithoutDuplicates, Matchers.containsInRelativeOrder(5, 0, 3, 1, 2));
	}

	@Test
	public void givenListContainsDuplicates_whenRemovingDuplicatesWithGuava_thenCorrect() {
		final List<Integer> listWithDuplicates = Lists.newArrayList(5, 0, 3, 1, 2, 3, 0, 0);
		final List<Integer> listWithoutDuplicates = Lists.newArrayList(Sets.newHashSet(listWithDuplicates));
		assertThat(listWithDuplicates, hasSize(8));
		assertThat(listWithoutDuplicates, hasSize(5));
		assertThat(listWithoutDuplicates, Matchers.containsInAnyOrder(5, 0, 3, 1, 2));
	}

	@Test
	public void givenListContainsDuplicates_whenRemovingDuplicatesPreservingOrderWithGuava_thenCorrect() {
		final List<Integer> listWithDuplicates = Lists.newArrayList(5, 0, 3, 1, 2, 3, 0, 0);
		final List<Integer> listWithoutDuplicates = Lists.newArrayList(Sets.newLinkedHashSet(listWithDuplicates));
		assertThat(listWithDuplicates, hasSize(8));
		assertThat(listWithoutDuplicates, hasSize(5));
		assertThat(listWithoutDuplicates, Matchers.containsInRelativeOrder(5, 0, 3, 1, 2));
	}

}