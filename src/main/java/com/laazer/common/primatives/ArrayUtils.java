package com.laazer.common.primatives;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *  Utilities for Java Collections and array primitive types
 */
public class ArrayUtils {

    public static List<Integer> toIntList(int[] arr) {
        List<Integer> res = new ArrayList<Integer>(arr.length);
        for(Integer i : iterable(arr)) {
            res.add(i);
        }
        return res;
    }

    public static int[] toIntArray(List<Integer> list) {
        int[] res = new int[list.size()];
        for(int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    public static Iterator<Integer> iterator(int[] arr) {
         return new PrimIntArrIterator(arr);
    }

    public static Iterable<Integer> iterable(int[] arr) {
        return new PrimIntArrIterator(arr);
    }



    /*************************************************************************/

    public static List<Character> toCharList(char[] arr) {
        List<Character> res = new ArrayList<Character>(arr.length);
        for(Character i : iterable(arr)) {
            res.add(i);
        }
        return res;
    }

    public static int[] toCharArray(List<Integer> list) {
        int[] res = new int[list.size()];
        for(int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    public static Iterator<Character> iterator(char[] arr) {
        return new PrimCharArrIterator(arr);
    }

    public static Iterable<Character> iterable(char[] arr) {
        return new PrimCharArrIterator(arr);
    }

}


abstract class APrimIterator<T> implements Iterable<T>, Iterator<T> {
    protected int i;
    APrimIterator() {
        i = 0;
    }

    public void remove() {
        throw new UnsupportedOperationException("");
    }

    public Iterator<T> iterator() {
        return this;
    }
}

class PrimIntArrIterator extends APrimIterator<Integer> {

    private int[] arr;
    PrimIntArrIterator(int[] arr) {
        super();
        this.arr = arr;
    }

    public boolean hasNext() {
        return i < arr.length;
    }

    public Integer next() {
        return arr[i++];
    }

}

class PrimLongArrIterator extends APrimIterator<Long> {

    private long[] arr;
    PrimLongArrIterator(long[] arr) {
        super();
        this.arr = arr;
    }

    public boolean hasNext() {
        return i < arr.length;
    }

    public Long next() {
        return arr[i++];
    }

}

class PrimFloatArrIterator extends APrimIterator<Float> {

    private float[] arr;
    PrimFloatArrIterator(float[] arr) {
        super();
        this.arr = arr;
    }

    public boolean hasNext() {
        return i < arr.length;
    }

    public Float next() {
        return arr[i++];
    }

}

class PrimDoubleArrIterator extends APrimIterator<Double> {

    private double[] arr;
    PrimDoubleArrIterator(double[] arr) {
        super();
        this.arr = arr;
    }

    public boolean hasNext() {
        return i < arr.length;
    }

    public Double next() {
        return arr[i++];
    }

}

class PrimCharArrIterator extends APrimIterator<Character> {

    private char[] arr;
    PrimCharArrIterator(char[] arr) {
        super();
        this.arr = arr;
    }

    public boolean hasNext() {
        return i < arr.length;
    }

    public Character next() {
        return arr[i++];
    }
}

class PrimBoolArrIterator extends APrimIterator<Boolean> {

    private boolean[] arr;
    PrimBoolArrIterator(boolean[] arr) {
        super();
        this.arr = arr;
    }

    public boolean hasNext() {
        return i < arr.length;
    }

    public Boolean next() {
        return arr[i++];
    }
}

