package com.khauminhduy.map;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.stream.Stream;

public class MapUtil {

	public static <K, V> Stream<K> keys(Map<K, V> map, V value) {
		return map.entrySet()
				.stream()
				.filter(entry -> value.equals(entry.getValue()))
				.map(Map.Entry::getKey);
	}

	public static <K, V> Set<K> getKeys(Map<K, V> map, V value) {
		Set<K> set = new HashSet<>();
		for (Entry<K, V> entry : map.entrySet()) {
			if (entry.getValue().equals(value)) {
				set.add(entry.getKey());
			}
		}
		return set;
	}

	public static <K, V> K getKey(Map<K, V> map, V value) {
		for (Entry<K, V> entry : map.entrySet()) {
			if (entry.getValue().equals(value)) {
				return entry.getKey();
			}
		}
		return null;
	}

}
