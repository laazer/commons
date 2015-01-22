package com.laazer.common.collections;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import java.util.*;
import java.util.Map.Entry;

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

    public static  <K,V> boolean replace(Map<K,V> map, K key, V oldValue, V newValue) {
       V v = map.get(key);
       if(v.equals(oldValue)) {
           map.put(key, newValue);
           return true;
       }
       else return false;
    }

    public static <K,V, T> Map<K, T> mapValues(Map<K,V> map, Function<? super V, T> f) {
        Map<K, T> result = new HashMap<K,T>();
        for (K key : map.keySet()) {
            result.put(key, f.apply(map.get(key)));
        }
        return result;
    }

    public static <K,V> KVPair<K,V> getPair(Map<K,V> map, K key) {
        return new KVPair<K,V>(key, map.get(key));
    }

    public static <K,V> Entry<K,V> getEntry(Map<K, V> map , K key) {
        return getPair(map, key);
    }

    public static <K,V> Set<KVPair<K,V>> pairSet(Map<K,V> map) {
        Set<KVPair<K,V>> result = new HashSet<KVPair<K,V>>();
        for (K key : map.keySet()) {
            result.add(getPair(map, key));
        }
        return result;
    }

    public static <K,V> List<KVPair<K,V>> pairList(Map<K,V> map) {
        List<KVPair<K,V>> result = new ArrayList<KVPair<K, V>>();
        Map<K,V> lmap = new HashMap<K, V>();
        lmap.putAll(map);
        for (K key : lmap.keySet()) {
            while(lmap.containsKey(key)) {
                result.add(getPair(lmap, key));
                lmap.remove(key);
            }
        }
        return result;
    }

    public static <K,V> List<? extends Entry<K,V>> entryList(Map<K,V> map) {
        return pairList(map);
    }

    public static <K,V> Map<K,V> toMap(Collection<? extends Entry<K,V>> collection) {
        Map<K, V> map = new HashMap<K, V>();
        for (Entry<K,V> p : collection) {
            map.put(p.getKey(), p.getValue());
        }
        return map;
    }

    public static <K,V> Map<K,V> filter(Map<K,V> map, Predicate<? super Entry<K, V>> pred) {
        return toMap(CollectionUtils.filter(pairList(map), pred));
    }

}
