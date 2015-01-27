package com.laazer.common.collections;

import com.laazer.common.functions.BinFunction;
import com.laazer.common.functions.Functions;

import java.util.*;

import com.google.common.base.Function;

public class ListUtils {


    public static <K, T> List<T> map(List<? extends K> list, Function<? super K, T> f) {
        List<T> result = new ArrayList<T>();
        for(int i = 0; i < list.size(); i++) {
            result.add(f.apply(list.get(i)));
        }
        return result;
    }

    public static <K, T> T fold(T base, BinFunction<T, K, T> f, List<? extends K> list) {
        if(list.isEmpty()) {return base;}
        else {
            K tmp = list.remove(0);
            return fold(f.apply(base, tmp), f, list);
        }
    }

    public static BinFunction<List, List, List> append = Functions.toBinFunction(new Append());
    private static class Append<T> implements Function<List<T>, Function<List<T>, List<T>>> {
        @Override
        public Function<List<T>, List<T>> apply(List<T> value) {
            return new Append1(value);
        }
        private class Append1 implements Function<List<T>, List<T>> {
            List<T> x;
            Append1(List<T> x) {
                this.x = x;
            }
            @Override
            public List<T> apply(List<T> value) {
                List<T> result = new ArrayList<T>();
                result.addAll(x); result.addAll(value);
                return result;
            }
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
