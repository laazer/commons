package com.laazer.common.collections;

import com.laazer.common.functions.BinFunction;
import com.laazer.common.functions.Functions;

import java.util.*;

import com.google.common.base.Function;

public class ListUtils {

    public static <T> MagicList<T> magic(List<T> stuffing) {
        return new MagicList(stuffing);
    }

    public static Integer sum(List<Integer> list) {
        return ListUtils.fold(0, Functions.add, list);
    }

    public static <K, T> List<T> map(List<? extends K> list, Function<? super K, T> f) {
        List<T> result = new ArrayList<T>();
        for(int i = 0; i < list.size(); i++) {
            result.add(f.apply(list.get(i)));
        }
        return result;
    }
    
    public static <T> List<T> arrayToList(T... array) {
        List<T> list = new ArrayList<T>();
        for(int i = 0; i < array.length; i++) {
            list.add(array[i]);
        }
        return list;
    }

    public static <K, T> T fold(T base, BinFunction<T, K, T> f, List<K> list) {
        if(list.isEmpty()) {return base;}
        else {
            K tmp = list.remove(0);
            return fold(f.apply(base, tmp), f, list);
        }
    }
    
    private static class ToSame<T> implements Function<T, T> {
        @Override
        public T apply(T value) {
            return value;
        }
        
    }
    public static Function<Object, List<Object>> toList = new ToList();
    private static class ToList implements Function<Object, List<Object>> {
        @Override
        public List<Object> apply(Object value) {
            return (List<Object>) value;
        }
        
    }
    
    public static Function<Object, List<String>> toStringList = new ToStringList();
    private static class ToStringList implements Function<Object, List<String>> {
        @Override
        public List<String> apply(Object value) {
            return ListUtils.map(ListUtils.toList.apply(value), Functions.toString);
        }
    }
    
    public static Function<Object, List<Integer>> toIntList = new ToIntList();
    private static class ToIntList implements Function<Object, List<Integer>> {
        @Override
        public List<Integer> apply(Object value) {
            return ListUtils.map(ListUtils.toList.apply(value), Functions.toInt);
        }
    }

    public static Function<Object, List<Double>> toDoubleList = new ToDoubleList();
    private static class ToDoubleList implements Function<Object, List<Double>> {
        @Override
        public List<Double> apply(Object value) {
            return ListUtils.map(ListUtils.toList.apply(value), Functions.toDouble);
        }
    }
}

class MagicList<K> implements List<K> {
    List<K> rabbit;
    public MagicList(List<K> rabbit) {
        this.rabbit = rabbit;
    }

    public <T> MagicList<T> map(Function<? super K, T> f) {
       return new MagicList<>(ListUtils.map(rabbit, f));
    }

    public <T> T fold(T base, BinFunction<T, K, T> f) {
        return ListUtils.fold(base, f, rabbit);
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

    @Override
    public int size() {
        return rabbit.size();
    }

    @Override
    public boolean isEmpty() {
        return rabbit.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return rabbit.contains(o);
    }

    @Override
    public Iterator<K> iterator() {
        return rabbit.iterator();
    }

    @Override
    public Object[] toArray() {
        return rabbit.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return rabbit.toArray(a);
    }

    @Override
    public boolean add(K k) {
        return rabbit.add(k);
    }

    @Override
    public boolean remove(Object o) {
        return rabbit.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return rabbit.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends K> c) {
        return rabbit.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends K> c) {
        return rabbit.addAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return rabbit.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return rabbit.retainAll(c);
    }

    @Override
    public void clear() {
        rabbit.clear();
    }

    @Override
    public K get(int index) {
        return rabbit.get(index);
    }

    @Override
    public K set(int index, K element) {
        return rabbit.set(index, element);
    }

    @Override
    public void add(int index, K element) {

    }

    @Override
    public K remove(int index) {
        return rabbit.remove(index);
    }

    @Override
    public int indexOf(Object o) {
        return rabbit.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return rabbit.lastIndexOf(o);
    }

    @Override
    public ListIterator<K> listIterator() {
        return rabbit.listIterator();
    }

    @Override
    public ListIterator<K> listIterator(int index) {
        return rabbit.listIterator(index);
    }

    @Override
    public List<K> subList(int fromIndex, int toIndex) {
        return rabbit.subList(fromIndex, toIndex);
    }
}
