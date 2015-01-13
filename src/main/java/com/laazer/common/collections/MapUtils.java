package com.laazer.common.collections;

import java.util.Map;

/**
 * Class that contains utilities for Java {@code Map}
 */
public class MapUtils {

    public static <K,V> V getOrDefault(Map<K, V> map, K key, V defaultValue) {
        if(map.containsKey(key)) return map.get(key);
        else return defaultValue;
    }

    public static <K,V> V putIfAbsent(Map<K,V> map, K key, V value) {
        if(map.containsKey(key)) {
            return map.get(key);
        }
        else return map.put(key, value);
    }

    public boolean remove(Map map, Object key) {
        try {
            Object o = map.get(key);
            if(o == null) return true;
            return (map.remove(key) != null);
        } catch (NullPointerException e) {
            return false;
        }
    }

    public <K,V> boolean replace(Map<K,V> map, K key, V oldValue, V newValue) {
       V v = map.get(key);
       if(v.equals(oldValue)) {
           map.put(key, newValue);
           return true;
       }
       else return false;
    }

}
