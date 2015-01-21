package com.laazer.common.collections;

import com.google.common.base.Function;
import com.google.common.base.Predicate;

import java.util.*;

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

    public <K,V, T> Map<K, T> mapValues(Map<K,V> map, Function<? super V, T> f) {
        Map<K, T> result = new HashMap<K,T>();
        for (K key : map.keySet()) {
            result.put(key, f.apply(map.get(key)));
        }
        return result;
    }

    public <K,V> Pair<K,V> getPair(Map<K,V> map, K key) {
        return new Pair<K,V>(key, map.get(key));
    }

    public <K,V> Set<Pair<K,V>> toPairSet(Map<K,V> map) {
        Set<Pair<K,V>> result = new HashSet<Pair<K,V>>();
        for (K key : map.keySet()) {
            result.add(getPair(map, key));
        }
        return result;
    }

    public <K,V> List<Pair<K,V>> toPairList(Map<K,V> map) {
        List<Pair<K,V>> result = new ArrayList<Pair<K, V>>();
        for (K key : map.keySet()) {
            while(map.containsKey(key)) {
                result.add(getPair(map, key));
                map.remove(key);
            }
        }
        return result;
    }

    public <K,V> Map<K,V> toMap(Collection<Pair<K,V>> collection) {
        Map<K, V> map = new HashMap<K, V>();
        for (Pair<K,V> p : collection) {
            map.put(p.getKey(), p.getValue());
        }
        return map;
    }

    public <K,V> Map<K,V> filter(Map<K,V> map, Predicate<? super Pair<K, V>> pred) {
        return toMap(CollectionUtils.filter(toPairList(map), pred));
    }
}
