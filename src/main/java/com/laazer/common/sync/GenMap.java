package com.laazer.common.sync;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
/**
 * a synchronized map
 */
public class GenMap<K, V> implements Map<K, V> {
    private Map<K, V> map = new HashMap<K,V>();
    private final Object Lock = new Object();

    
    public int size() {
        synchronized (Lock) {
            return map.size();
        }
    }

    
    public boolean isEmpty() {
        synchronized (Lock) {
            return map.isEmpty();
        }
    }

    
    public boolean containsKey(Object key) {
        synchronized (Lock) {
            return map.containsKey(key);
        }
    }

    
    public boolean containsValue(Object value) {
        synchronized (Lock) {
            return map.containsValue(value);
        }
    }

    
    public V get(Object key) {
        synchronized (Lock) {
            return map.get(key);
        }
    }

    
    public V put(K key, V value) {
        synchronized (Lock) {
            return map.put(key, value);
        }
    }

    
    public V remove(Object key) {
        synchronized (Lock) {
            return map.remove(key);
        }
    }

    
    public void putAll(Map<? extends K, ? extends V> m) {
        synchronized (Lock) {
            map.putAll(m);
        }
    }

    
    public void clear() {
        synchronized (Lock) {
            map.clear();
        }
    }

    
    public Set<K> keySet() {
        synchronized (Lock) {
            return map.keySet();
        }
    }

    
    public Collection<V> values() {
        synchronized (Lock) {
            return map.values();
        }
    }

    
    public Set<Entry<K, V>> entrySet() {
        synchronized (Lock) {
            return map.entrySet();
        }
    }
}
