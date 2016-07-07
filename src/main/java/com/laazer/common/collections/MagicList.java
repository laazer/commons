package com.laazer.common.collections;

import com.laazer.common.functions.BinaryFunction;
import com.laazer.common.functions.Function;
import com.laazer.common.functions.Predicate;

import java.util.*;

/**
 * Created by laazer on
 * Java List wrapper that contains more functionality then
 * the built in List
 */
public class MagicList<K> implements List<K> {
    List<K> rabbit;
    public MagicList(List<K> list) {
        this.rabbit = list;
    }

    public MagicList(K... items) {
        this.rabbit = Arrays.asList(items);
    }

    public <T> MagicList<T> map(Function<? super K, T> f) {
        return new MagicList<T>(ListUtils.map(rabbit, f));
    }

    public <T> T fold(T base, BinaryFunction<T, K, T> f) {
        return CollectionUtils.fold(base, f, rabbit);
    }

    public MagicList<K> filter(Predicate<K> f) {
        rabbit = (List<K>) CollectionUtils.filter(this.rabbit, f);
        return this;
    }

    public void swap(int i, int j) {
        Collections.swap(rabbit, i, j);
    }

    public void reverse() {
        Collections.reverse(rabbit);
    }

    public int binarySearch(K key, Comparator<? super K> comp) {
        return Collections.binarySearch(rabbit, key, comp);
    }

    
    public int size() {
        return rabbit.size();
    }

    
    public boolean isEmpty() {
        return rabbit.isEmpty();
    }

    
    public boolean contains(Object o) {
        return rabbit.contains(o);
    }

    
    public Iterator<K> iterator() {
        return rabbit.iterator();
    }

    
    public Object[] toArray() {
        return rabbit.toArray();
    }

    
    public <T> T[] toArray(T[] a) {
        return rabbit.toArray(a);
    }

    
    public boolean add(K k) {
        return rabbit.add(k);
    }

    
    public boolean remove(Object o) {
        return rabbit.remove(o);
    }

    
    public boolean containsAll(Collection<?> c) {
        return rabbit.containsAll(c);
    }

    
    public boolean addAll(Collection<? extends K> c) {
        return rabbit.addAll(c);
    }

    
    public boolean addAll(int index, Collection<? extends K> c) {
        return rabbit.addAll(c);
    }

    
    public boolean removeAll(Collection<?> c) {
        return rabbit.removeAll(c);
    }

    
    public boolean retainAll(Collection<?> c) {
        return rabbit.retainAll(c);
    }

    
    public void clear() {
        rabbit.clear();
    }

    
    public K get(int index) {
        return rabbit.get(index);
    }

    
    public K set(int index, K element) {
        return rabbit.set(index, element);
    }

    
    public void add(int index, K element) {
        this.rabbit.add(index, element);
    }

    
    public K remove(int index) {
        return rabbit.remove(index);
    }

    
    public int indexOf(Object o) {
        return rabbit.indexOf(o);
    }

    
    public int lastIndexOf(Object o) {
        return rabbit.lastIndexOf(o);
    }

    
    public ListIterator<K> listIterator() {
        return rabbit.listIterator();
    }

    
    public ListIterator<K> listIterator(int index) {
        return rabbit.listIterator(index);
    }

    
    public List<K> subList(int fromIndex, int toIndex) {
        return rabbit.subList(fromIndex, toIndex);
    }

    
    public boolean equals(Object o) {
        if(o == null) return false;
        else if (o instanceof List) {
            List list = (List)o;
            return list.equals(this.rabbit);
        }
        else return false;
    }

    
    public int hashCode() {
        return this.rabbit.hashCode();
    }
}
