package com.laazer.common.primitives;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *  Utilities for Java Collections and array primitive types
 */
public class ArrayUtils {


    public final static void insert(int[] arr, int value) {
        int tmp = value;
        for(int i = 0; i < arr.length; i++) {
            if (value < arr[i]) {
                tmp = arr[i];
                arr[i] = value;
            }
        }
    }

    public final static void insert(char[] arr, char value) {
        char tmp = value;
        for(int i = 0; i < arr.length; i++) {
            if (value < arr[i]) {
                tmp = arr[i];
                arr[i] = value;
            }
        }
    }

    public final static List<Integer> toList(int[] arr) {
        List<Integer> res = new ArrayList<Integer>(arr.length);
        for(Integer i : iterable(arr)) {
            res.add(i);
        }
        return res;
    }

    public final static int[] toIntArray(List<Integer> list) {
        int[] res = new int[list.size()];
        for(int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    public final static Iterator<Integer> iterator(int[] arr) {
         return new PrimIntArrIterator(arr);
    }

    public final static Iterable<Integer> iterable(int[] arr) {
        return new PrimIntArrIterator(arr);
    }

    /*************************************************************************/

    public final static List<Short> toList(short[] arr) {
        List<Short> res = new ArrayList<Short>(arr.length);
        for(Short i : iterable(arr)) {
            res.add(i);
        }
        return res;
    }

    public final static short[] toShortArray(List<Short> list) {
        short[] res = new short[list.size()];
        for(int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    public final static Iterator<Short> iterator(short[] arr) {
        return new PrimShortArrIterator(arr);
    }

    public final static Iterable<Short> iterable(short[] arr) {
        return new PrimShortArrIterator(arr);
    }

    /*************************************************************************/

    public final static List<Long> toList(long[] arr) {
        List<Long> res = new ArrayList<Long>(arr.length);
        for(Long i : iterable(arr)) {
            res.add(i);
        }
        return res;
    }

    public final static long[] toLongArray(List<Long> list) {
        long[] res = new long[list.size()];
        for(int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    public final static Iterator<Long> iterator(long[] arr) {
        return new PrimLongArrIterator(arr);
    }

    public final static Iterable<Long> iterable(long[] arr) {
        return new PrimLongArrIterator(arr);
    }

    /*************************************************************************/

    public final static List<Float> toList(float[] arr) {
        List<Float> res = new ArrayList<Float>(arr.length);
        for(Float i : iterable(arr)) {
            res.add(i);
        }
        return res;
    }

    public final static float[] toFloatArray(List<Float> list) {
        float[] res = new float[list.size()];
        for(int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    public final static Iterator<Float> iterator(float[] arr) {
        return new PrimFloatArrIterator(arr);
    }

    public final static Iterable<Float> iterable(float[] arr) {
        return new PrimFloatArrIterator(arr);
    }

    /*************************************************************************/

    public final static List<Double> toList(double[] arr) {
        List<Double> res = new ArrayList<Double>(arr.length);
        for(Double i : iterable(arr)) {
            res.add(i);
        }
        return res;
    }

    public final static double[] toDoubleArray(List<Double> list) {
        double[] res = new double[list.size()];
        for(int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    public final static Iterator<Double> iterator(double[] arr) {
        return new PrimDoubleArrIterator(arr);
    }

    public final static Iterable<Double> iterable(double[] arr) {
        return new PrimDoubleArrIterator(arr);
    }

    /*************************************************************************/

    public final static List<Character> toList(char[] arr) {
        List<Character> res = new ArrayList<Character>(arr.length);
        for(Character i : iterable(arr)) {
            res.add(i);
        }
        return res;
    }

    public final static char[] toCharArray(List<Character> list) {
        char[] res = new char[list.size()];
        for(int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    public final static Iterator<Character> iterator(char[] arr) {
        return new PrimCharArrIterator(arr);
    }

    public final static Iterable<Character> iterable(char[] arr) {
        return new PrimCharArrIterator(arr);
    }

    /*************************************************************************/

    public final static List<Byte> toList(byte[] arr) {
        List<Byte> res = new ArrayList<Byte>(arr.length);
        for(Byte i : iterable(arr)) {
            res.add(i);
        }
        return res;
    }

    public final static byte[] toByteArray(List<Byte> list) {
        byte[] res = new byte[list.size()];
        for(int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    public final static Iterator<Byte> iterator(byte[] arr) {
        return new PrimByteArrIterator(arr);
    }

    public final static Iterable<Byte> iterable(byte[] arr) {
        return new PrimByteArrIterator(arr);
    }

    /*************************************************************************/

    public final static List<Boolean> toList(boolean[] arr) {
        List<Boolean> res = new ArrayList<Boolean>(arr.length);
        for(Boolean i : iterable(arr)) {
            res.add(i);
        }
        return res;
    }

    public final static boolean[] toBoolArray(List<Boolean> list) {
        boolean[] res = new boolean[list.size()];
        for(int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    public final static Iterator<Boolean> iterator(boolean[] arr) {
        return new PrimBoolArrIterator(arr);
    }

    public final static Iterable<Boolean> iterable(boolean[] arr) {
        return new PrimBoolArrIterator(arr);
    }

    /*************************************************************************/

    public final static <T> List<T> toList(T[] arr) {
        List<T> res = new ArrayList<T>(arr.length);
        for(T i : iterable(arr)) {
            res.add(i);
        }
        return res;
    }

    public final static <T> Iterator<T> iterator(T[] arr) {
        return new SimpleIterator<T>(arr);
    }

    public final static <T> Iterable<T> iterable(T[] arr) {
        return new SimpleIterator<T>(arr);
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

class SimpleIterator<T> extends APrimIterator<T> {
    private T[] arr;
    SimpleIterator(T[] arr) {
        this.arr = arr;
    }

    public boolean hasNext() {
        return i < arr.length;
    }

    public T next() {
        return arr[i++];
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

class PrimShortArrIterator extends APrimIterator<Short> {

    private short[] arr;
    PrimShortArrIterator(short[] arr) {
        super();
        this.arr = arr;
    }

    public boolean hasNext() {
        return i < arr.length;
    }

    public Short next() {
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

class PrimByteArrIterator extends APrimIterator<Byte> {

    private byte[] arr;
    PrimByteArrIterator(byte[] arr) {
        super();
        this.arr = arr;
    }

    public boolean hasNext() {
        return i < arr.length;
    }

    public Byte next() {
        return arr[i++];
    }
}

