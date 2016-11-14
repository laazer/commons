package com.laazer.common.collections;

import com.laazer.common.functions.BinaryFunction;
import com.laazer.common.functions.Functions;
import com.laazer.common.functions.Function;
import com.laazer.common.primitives.DoubleUtils;
import com.laazer.common.primitives.IntUtils;

import java.util.*;

public class ListUtils {

    public final static <K, T> List<T> map(List<? extends K> list, Function<? super K, T> f) {
        List<T> result = new ArrayList<T>(list.size());
        for(int i = 0; i < list.size(); i++) {
            result.add(f.apply(list.get(i)));
        }
        return result;
    }

    public final static <K, T> T fold(T base, BinaryFunction<T, K, T> f, List<? extends K> list) {
        if(list.isEmpty()) {return base;}
        else {
            K tmp = list.remove(0);
            return fold(f.apply(base, tmp), f, list);
        }
    }

    @Deprecated
    public final static BinaryFunction<List<Object>, List<Object>, List<Object>> append = append();
    public final static <T> BinaryFunction<List<T>, List<T>, List<T>> append() {
        return Functions.toBinFunction(new Append<T>());
    }

    private static class Append<T> implements Function<List<T>, Function<List<T>, List<T>>> {
        
        public Function<List<T>, List<T>> apply(List<T> value) {
            return new Append1(value);
        }
        private class Append1 implements Function<List<T>, List<T>> {
            List<T> x;
            Append1(List<T> x) {
                this.x = x;
            }
            
            public List<T> apply(List<T> value) {
                List<T> result = new ArrayList<T>();
                result.addAll(x); result.addAll(value);
                return result;
            }
        }
    }

    public final static Function<Object, List<Object>> toList = new ToList();
    private static class ToList implements Function<Object, List<Object>> {
        public List<Object> apply(Object value) {
            return (List<Object>) value;
        }
        
    }
    
    public final static Function<Object, List<String>> toStringList = new ToStringList();
    private static class ToStringList implements Function<Object, List<String>> {
        
        public List<String> apply(Object value) {
            return ListUtils.map(ListUtils.toList.apply(value), Functions.toStringObject());
        }
    }
    
    public final static Function<Object, List<Integer>> toIntList = new ToIntList();
    private static class ToIntList implements Function<Object, List<Integer>> {
        
        public List<Integer> apply(Object value) {
            return ListUtils.map(ListUtils.toList.apply(value), IntUtils.toInt());
        }
    }

    public final static Function<Object, List<Double>> toDoubleList = new ToDoubleList();
    private static class ToDoubleList implements Function<Object, List<Double>> {
        
        public List<Double> apply(Object value) {
            return ListUtils.map(ListUtils.toList.apply(value), DoubleUtils.toDouble());
        }
    }
}
