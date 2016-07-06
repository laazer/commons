package com.laazer.common.collections;

import java.util.Map.Entry;

/**
 * Created by laazer on 1/21/15.
 *
 * Simple Key-Value Pair class for {@code Map}s
 */
public class KVPair<K,V> implements Entry<K,V>{

    K key;
    V value;
    public KVPair(K key, V value) {
       this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }
    
    public V getValue() {
        return value;
    }
    
    public V setValue(V value) {
        this.value = value;
        return this.value;
    }

    public boolean equals(Object o) {
        if(o == null) return false;
        else if(o  instanceof Entry) {
            Entry e = (Entry)o;
            return e.getKey().equals(this.key) &&
                    e.getValue().equals(this.value);
        }
        else return false;
    }

    
    public int hashCode() {
        return (key.hashCode() * 11) * (value.hashCode()/7);
    }
}
