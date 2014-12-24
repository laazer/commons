package com.laazer.common;

import java.util.ArrayList;
import java.util.List;
import com.google.common.base.Function;

/**
 * Class containing common functions
 * @author Jacob
 *
 */
public class Functions {

    public static Function<Object, String> toString = new ToString();
    private static class ToString implements Function<Object, String> {
        @Override
        public String apply(Object value) {
            return value.toString();
        }
    }
    
    public static Function<Object, Double> toDouble = new ToDouble();
    private static class ToDouble implements Function<Object, Double> {
        @Override
        public Double apply(Object value) {
            return (Double) value;
        }
    }
    
    public static Function<Object, Integer> toInt = new ToInt();
    private static class ToInt implements Function<Object, Integer> {
        @Override
        public Integer apply(Object value) {
            return Integer.parseInt(value.toString());
        }
    }
    
    public static Function<Object, Boolean> toBoolean = new ToBoolean();
    private static class ToBoolean implements Function<Object, Boolean> {
        @Override
        public Boolean apply(Object value) {
            return Boolean.parseBoolean(value.toString());
        }
    }
    
    public static BinFunction<List, List, List> append = new Append();
    private static class Append<T> implements BinFunction<List<T>, List<T>, List<T>> {
        public List<T> apply(List<T> value1, List<T> value2) {
            List<T> result = new ArrayList<T>();
            result.addAll(value1); result.addAll(value2);
            return result;
        }
    }

    /**
     * Transforms a {@code Function} into a {@code BinFunction}
     * @param function a given {@code Function}
     * @param <X> first input value type
     * @param <Y> second input value type
     * @param <Z> function return type
     * @return a {@code BinFunction} representation of the given {@code Function}
     */
    private static <X, Y, Z> BinFunction<X, Y, Z> toBinFunction(Function<X, Function<Y, Z>> function) {
        class CompFun implements BinFunction<X, Y, Z> {
            @Override
            public Z apply(X value1, Y value2) {
                return function.apply(value1).apply(value2);
            }
        }
        return new CompFun();
    }
    
    public static BinFunction<Integer, Integer, Integer> add = new Add();
    private static class Add implements BinFunction<Integer, Integer, Integer> {
        public Integer apply(Integer value1, Integer value2) {
            return value1 + value2;
        }
        
    }
    
    private static BinFunction<Object, Object, Boolean> equals = new Equals();
    private static class Equals implements BinFunction<Object, Object, Boolean> {
        public Boolean apply(Object value1, Object value2) {
            return value1.equals(value2);
        }
    }
}