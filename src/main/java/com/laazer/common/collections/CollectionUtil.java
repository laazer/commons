package com.laazer.common.collections;

import java.util.*;
import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.laazer.common.functions.Functions;

/**
 * A class that contains specialty methods for handling Collections.
 * All methods in this class should be Static
 */
public class CollectionUtil {

    private final static String LINE_SEPARATOR = System.getProperty("line.separator");

    /**
     * returns a string where each object in the give {@code Collection} is placed on a new line
     * (i.e [foo, bar, bizz] -> <br/> foo <br/> bar <br/> bizz)
     * @param collection a given {@code Collection} of type {@code T}
     * @return
     */
    public static <T> String lineSeparatedCollection(Collection<T> collection) {
        StringBuffer sb = new StringBuffer("");
        for (T item : collection) {
            sb = sb.append(item.toString()).append(LINE_SEPARATOR);
        }
        return sb.deleteCharAt(sb.lastIndexOf(LINE_SEPARATOR)).toString();
    }

    /**
     * returns a string where each object in the give {@code Collection} is placed on a new line
     * (i.e [foo, bar, bizz] -> <br/> foo <br/> bar <br/> bizz)
     * @param collection a given {@code Array} of type {@code T}
     * @return
     */
    public static <T> String lineSeparatedCollection(T... collection) {
        StringBuffer sb = new StringBuffer("");
        for (T item : collection) {
            sb = sb.append(item.toString()).append(LINE_SEPARATOR);
            sb = sb.append(item.toString()).append(System.getProperty("line.separator"));
        }
        return sb.deleteCharAt(sb.lastIndexOf(LINE_SEPARATOR)).toString();
    }

    /**
     * Extension to the add method that adds an {@code Array} of Objects of type
     * {@code T} to the given {@code Collection} of {@code T}
     * @param collection the Collection of type {@code Collection}
     * @param ts the {@code Collection} of objects to add to the given {@code Collection}
     * @param <T> the given {@code Collection} Type
     */
    public static <T> void addCollection(Collection<T> collection, T... ts) {
        for (T t : ts) { collection.add(t); }
    }

    /**
     * Extension to the remove method that removes an {@code Array} of Objects of type
     * {@code T} from the given {@code Collection} of {@code T}
     * @param collection the Collection of type {@code Collection}
     * @param ts the collection of objects to remove from the given {@code Collection}
     * @param <T> the given {@code Collection} Type
     */
    public static <T> void removeCollection(Collection<T> collection, T... ts) {
        for (T t : ts) { collection.remove(t); }
    }

    /**
     * Uses the given {@code Collection}'s add method to add a value to the given {@code Collection}
     * @param collection a given {@code Collection} of type {@code T}
     * @param value a given value of type {@code T}
     * @param <T> the type of {@code Collection} and value
     * @return the given {@code Collection} with the given value added to it
     */
    public static <T> Collection<T> addAndReturn(Collection<T> collection, T value) {
        collection.add(value);
        return collection;
    }

    /**
     * Uses the given {@code Collection}'s add method to add a value to the given {@code Collection}
     * @param collection a given {@code Collection} of type {@code T}
     * @param values a given value of type {@code T}
     * @param <T> the type of {@code Collection} and value
     * @return the given {@code Collection} with the given values added to it
     */
    public static <T> Collection<T> addCollectionAndReturn(Collection<T> collection, T... values) {
        for (T t : values) { collection.add(t); }
        return collection;
    }

    /**
     * Uses the given {@code Collection}'s add method to add a value to the given {@code Collection}
     * @param collection a given {@code Collection} of type {@code T}
     * @param index the given index put put the given value
     * @param value a given value of type {@code T}
     * @param <T> the type of {@code Collection} and value
     * @return the given {@code Collection} with the given value added to it
     */
    public static <T> Collection<T> addAndReturn(Collection<T> collection, int index, T value) {
        ((List<T>)collection).add(index, value);
        return (Collection<T>) collection;
    }

    /**
     * A non-void version of the {@code Collections} reverse method that works on any
     * given {@code Collection}
     * @param collection a given {@code Collection}
     * @param <T>
     * @return the given {@code Collection} with all elements in reverse order
     */
    public static <T> Collection<T> reverse(Collection<T> collection) {
        List<T> result = new ArrayList<T>(collection);
        Collections.reverse(result);
        return result;
    }

    /**
     * Converts the given {@code Collection} into an {@code Array} of the type {@code T}
     * @param collection a given {@code Collection} of type {@code T}
     * @param <T> the {@code Collection} type
     * @return an {@code Array} of type {@code T}
     */
    public static <T> T[] toArray(Collection<T> collection) {
        Object[] result = new Object[collection.size()];
        int i = 0;
        for(T t : collection) { result[i] = t; i++; }
        return (T[]) result;
    }

    /**
     * Filters through a list
     * @param collection
     * @param predicate
     * @param <T>
     * @return
     */
    public static <T> Collection<T> filter(Collection<T> collection, Predicate<T> predicate) {
        Collection<T> tmpCol = collection;
        for (T t : tmpCol) {
            if (!predicate.apply(t)) collection.remove(t);
        }
        return collection;
    }

    public static <T> Collection<T> filter(Collection<T> collection, Function<T, Boolean> predicate) {
        return CollectionUtil.filter(collection, Functions.toPredicate(predicate));
    }

    public static int maxListSize(Collection<Collection> cs) {
        if (cs.isEmpty()) return -1;
        int large = 0;
        for (Collection c : cs) {
            large = Math.max(0, c.size());
        }
        return large;
    }

    public static int maxListSize(Collection... cs) {
        if (cs.length <= 0) return -1;
        else return maxListSize(cs[0], cs);
    }

    public static int maxListSize(Collection c1, Collection... cs) {
        int large = c1.size();
        for (Collection c : cs) {
            large = Math.max(large, c.size());
        }
        return large;
    }

    private static int maxListSize(Collection c1, Collection c2) {
        return Math.max(c1.size(), c2.size());
    }
}
