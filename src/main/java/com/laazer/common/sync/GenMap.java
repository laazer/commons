package com.laazer.common.sync;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
/**
 * a synchronized map
 */
public class GenMap<K, V> implements Map<K, V> {
    private Map<K, V> map = new HashMap<>();
    private final Object Lock = new Object();

    @Override
    public int size() {
        synchronized (Lock) {
            return map.size();
        }
    }

    @Override
    public boolean isEmpty() {
        synchronized (Lock) {
            return map.isEmpty();
        }
    }

    @Override
    public boolean containsKey(Object key) {
        synchronized (Lock) {
            return map.containsKey(key);
        }
    }

    @Override
    public boolean containsValue(Object value) {
        synchronized (Lock) {
            return map.containsValue(value);
        }
    }

    @Override
    public V get(Object key) {
        synchronized (Lock) {
            return map.get(key);
        }
    }

    @Override
    public V put(K key, V value) {
        synchronized (Lock) {
            return map.put(key, value);
        }
    }

    @Override
    public V remove(Object key) {
        synchronized (Lock) {
            return map.remove(key);
        }
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        synchronized (Lock) {
            map.putAll(m);
        }
    }

    @Override
    public void clear() {
        synchronized (Lock) {
            map.clear();
        }
    }

    @Override
    public Set<K> keySet() {
        synchronized (Lock) {
            return map.keySet();
        }
    }

    @Override
    public Collection<V> values() {
        synchronized (Lock) {
            return map.values();
        }
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        synchronized (Lock) {
            return map.entrySet();
        }
    }
}
