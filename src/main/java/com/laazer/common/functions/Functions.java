package com.laazer.common.functions;

import java.util.ArrayList;
import java.util.List;
import com.google.common.base.Function;
import com.laazer.common.collections.ListUtils;

/**
 * Class containing common functions
 * @author Jacob
 *
 */
public class Functions {

    public static Function<Object, Object> identity = new Identity();
    private static class Identity<X> implements Function<X, X> {
        @Override
        public X apply(X value) {
            return value;
        }
    }

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

    /**
     * Transforms a {@code Function} into a {@code BinFunction}
     * @param function a given {@code Function}
     * @param <X> first input value type
     * @param <Y> second input value type
     * @param <Z> function return type
     * @return a {@code BinFunction} representation of the given {@code Function}
     */
    public static <X, Y, Z> BinFunction<X, Y, Z> toBinFunction(Function<X, Function<Y, Z>> function) {
        return new CompFun(function);
    }

    public static <X, Y, Z> Function<Y, Z> toUniFunction(BinFunction<X, Y, Z> function, X value) {
        return function.toUniFun(value);
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
                x = x;
            }
            @Override
            public List<T> apply(List<T> value) {
                List<T> result = new ArrayList<T>();
                result.addAll(x); result.addAll(value);
                return result;
            }
        }
    }

    public static BinFunction<Object, Object, Boolean> equals = Functions.toBinFunction(new Equals());
    private static class Equals implements Function<Object, Function<Object, Boolean>> {
        @Override
        public Function<Object, Boolean> apply(Object value) {
            return new Equals1(value);
        }
        private class Equals1 implements Function<Object, Boolean> {
            Object x;
            Equals1(Object x) {
                x = x;
            }
            @Override
            public Boolean apply(Object value) {
                return x.equals(value);
            }
        }
    }

    public static BinFunction<Integer, Integer, Integer> mult = Functions.toBinFunction(new Multiply());
    private static class Multiply implements Function<Integer, Function<Integer, Integer>> {
        @Override
        public Function<Integer, Integer> apply(Integer value) {
            return new Multiply1(value);
        }
        private class Multiply1 implements Function<Integer, Integer> {
            Integer x;
            Multiply1(Integer x) {
                x = x;
            }
            @Override
            public Integer apply(Integer value) {
                return x * value;
            }
        }
    }

    public static BinFunction<Long, Long, Long> multL = Functions.toBinFunction(new MultiplyL());
    private static class MultiplyL implements Function<Long, Function<Long, Long>> {
        @Override
        public Function<Long, Long> apply(Long value) {
            return new MultiplyL1(value);
        }
        private class MultiplyL1 implements Function<Long, Long> {
            Integer x;
            MultiplyL1(Long x) {
                x = x;
            }
            @Override
            public Long apply(Long value) {
                return x * value;
            }
        }
    }

    public static BinFunction<Integer, Integer, Integer> add = Functions.toBinFunction(new Add());
    private static class Add implements Function<Integer, Function<Integer, Integer>> {
        @Override
        public Function<Integer, Integer> apply(Integer value) {
            return new Add1(value);
        }
        private class Add1 implements Function<Integer, Integer> {
            Integer x;
            Add1(Integer x) {
                x = x;
            }
            @Override
            public Integer apply(Integer value) {
                return x + value;
            }
        }
    }

}

class CompFun<X, Y, Z> implements BinFunction<X, Y, Z> {
    Function<X, Function<Y, Z>> f;
    public CompFun(Function<X, Function<Y, Z>> f) {
        this.f = f;
    }
    public Z apply(X value1, Y value2) {
        return f.apply(value1).apply(value2);
    }

    public Function<Y, Z> toUniFun(X value) {
        return f.apply(value);
    }
}