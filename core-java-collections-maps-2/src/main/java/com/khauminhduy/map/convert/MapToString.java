package com.khauminhduy.map.convert;

import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.google.common.base.Joiner;

public class MapToString {
	
	public static String convertWithIteration(Map<Integer, ?> map) {
		StringBuilder strBuilder = new StringBuilder("{");
		for (Integer key : map.keySet()) {
			strBuilder.append(key + " = " + map.get(key) + ", ");
		}
		strBuilder.delete(strBuilder.length() - 2, strBuilder.length()).append("}");

		return strBuilder.toString();
	}

	public static String convertWithStream(Map<Integer, ?> map) {
		return map.keySet().stream().map(key -> key + " = " + map.get(key)).collect(Collectors.joining(",", "{", "}"));
	}

	public static String convertWithGuava(Map<Integer, ?> map) {
		return Joiner.on(",").withKeyValueSeparator("=").join(map);
	}

	public static String convertWithApache(Map<Integer, ?> map) {
		return StringUtils.join(map);
	}

}
