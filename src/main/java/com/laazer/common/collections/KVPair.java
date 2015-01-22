package com.laazer.common.collections;

/**
 * Created by laazer on 1/21/15.
 *
 * Simple Key-Value Pair class for {@code Map}s
 */
public class KVPair<K,V> {

    K key;
    V value;
    public KVPair(K key, V value) {
       this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }
}
