package com.laazer.common.collections;

import java.util.*;
import com.laazer.common.functions.BinaryFunction;
import com.laazer.common.functions.Function;
import com.laazer.common.functions.Functions;
import com.laazer.common.functions.Predicate;

/**
 * A class that contains specialty methods for handling Collections.
 * All methods in this class should be Static
 */
public class CollectionUtils {

    private final static String LINE_SEPARATOR = System.getProperty("line.separator");

    /**
     * returns a string where each object in the give {@code Collection} is placed on a new line
     * (i.e [foo, bar, bizz] -> <br/> foo <br/> bar <br/> bizz)
     * @param collection a given {@code Collection} of type {@code T}
     * @return
     */
    public final static <T> String lineSeparatedCollection(Collection<T> collection) {
        StringBuffer sb = new StringBuffer("");
        for (T item : collection) {
            sb.append(item.toString()).append(LINE_SEPARATOR);
        }
        return sb.deleteCharAt(sb.lastIndexOf(LINE_SEPARATOR)).toString();
    }

    /**
     * returns a string where each object in the give {@code Collection} is placed on a new line
     * (i.e [foo, bar, bizz] -> <br/> foo <br/> bar <br/> bizz)
     * @param collection a given {@code Array} of type {@code T}
     * @return
     */
    public final static <T> String lineSeparatedCollection(T... collection) {
        StringBuffer sb = new StringBuffer("");
        for (T item : collection) {
            sb.append(item.toString()).append(LINE_SEPARATOR);
        }
        return sb.deleteCharAt(sb.lastIndexOf(LINE_SEPARATOR)).toString();
    }

    /**
     * Filters through a list
     * @param collection
     * @param predicate
     * @param <T>
     * @return
     */
    public final static <T> Collection<T> filter(Collection<T> collection, Predicate<? super T> predicate) {
        Collection<T> tmpCol = new ArrayList<T>();
        tmpCol.addAll(collection);
        for (T t : collection) {
            if (!predicate.apply(t)) collection.remove(t);
        }
        return collection;
    }

    public final static <T> Collection<T> filter(Collection<T> collection, Function<? super T, Boolean> predicate) {
        return CollectionUtils.filter(collection, Functions.toPredicate(predicate));
    }

    public final static int maxListSize(Collection<? extends Collection> cs) {
        if (cs.isEmpty()) return -1;
        int large = 0;
        for (Collection c : cs) {
            large = Math.max(large, c.size());
        }
        return large;
    }

    public final static int maxListSize(Collection c1, Collection... cs) {
        int large = c1.size();
        for (Collection c : cs) {
            large = Math.max(large, c.size());
        }
        return large;
    }

    public final static <K, T> T fold(T base, BinaryFunction<T, K, T> f, Collection<? extends K> collection) {
        Iterator<? extends K> iterator = collection.iterator();
        K tmp;
        while (iterator.hasNext()) {
            tmp = iterator.next();
            base = f.apply(base, tmp);
        }
        return base;
    }

    public final static <K, T> T foldR(T base, BinaryFunction<T, K, T> f, Collection<? extends K> collection) {
        Object[] arr = collection.toArray();
        for (int i = arr.length - 1; i >= 0; i--) {
            base = f.apply(base, (K)arr[i]);
        }
        return base;
    }

    public final static <T> T find(Predicate<T> pred, Collection<? extends T> collection) {
        for(T t : collection) {
            if (pred.apply(t)) return t;
        }
        return null;
    }

    public final static Number sum(Collection<? extends Number> collection) {
        return CollectionUtils.fold(0, Functions.add, collection);
    }

    public final static Number product(Collection<? extends Number> collection) {
        if (collection.isEmpty()) return 0;
        return CollectionUtils.fold(1, Functions.mult, collection);
    }
}
