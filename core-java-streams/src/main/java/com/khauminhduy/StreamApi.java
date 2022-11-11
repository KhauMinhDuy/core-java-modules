package com.khauminhduy;

import java.util.List;
import java.util.stream.Stream;

public class StreamApi {

	public static String getLastElementUsingReduce(List<String> valueList) {
		return valueList.stream().reduce((first, second) -> second).orElse(null);
	}

	public static String getLastElementUsingSkip(List<String> valueList) {
		int size = valueList.size();
		return valueList.stream().skip(size - 1).findFirst().orElse(null);
	}

	public static Integer getInfiniteStreamLastElementUsingReduce() {
		Stream<Integer> stream = Stream.iterate(0, i -> i + 1);
		return stream.limit(20).reduce((pre, next) -> next).orElse(null);
	}

}
