package com.khauminhduy.map.copyhashmap;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CopyHashMap {

    public static <String, Employee> HashMap<String, Employee> copyUsingConstructor(HashMap<String, Employee> originalMap) {
        return new HashMap<>(originalMap);
    }

    public static <String, Employee> HashMap<String, Employee> copyUsingClone(HashMap<String, Employee> originalMap) {
        return (HashMap<String, Employee>) originalMap.clone();
    }

    public static <String, Employee> HashMap<String, Employee> copyUsingPut(HashMap<String, Employee> originalMap) {
        HashMap<String, Employee> shallowCopy = new HashMap<>();
        Set<Map.Entry<String, Employee>> entries = originalMap.entrySet();
        for(Map.Entry<String, Employee> mapEntry : entries) {
            shallowCopy.put(mapEntry.getKey(), mapEntry.getValue());
        }

        return shallowCopy;
    }

}
