package com.laazer.common.collections;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.laazer.common.functions.BinFunction;
import java.util.*;

/**
 * Created by laazer on
 */
public class MagicList<K> implements List<K> {
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

    public MagicList<K> filter(Predicate<K> f) {
        rabbit = (List<K>)CollectionUtil.filter(this.rabbit, f);
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
