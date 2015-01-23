package com.laazer.common.functions;

import java.util.ArrayList;
import java.util.List;
import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Lists;

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
            if (value instanceof String) {
                return Double.parseDouble(value.toString());
            }
            else if (value instanceof Number){
                return ((Number)value).doubleValue();
            }
            else {
                return (Double)value;
            }
        }
    }
    
    public static Function<Object, Integer> toInt = new ToInt();
    private static class ToInt implements Function<Object, Integer> {
        @Override
        public Integer apply(Object value) {
            if (value instanceof String) {
                return Integer.parseInt(value.toString());
            }
            else if (value instanceof Number){
                return ((Number)value).intValue();
            }
            else {
                return (Integer)value;
            }
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

    public static BinFunction<Object, Object, Boolean> equals = Functions.toBinFunction(new Equals());
    private static class Equals implements Function<Object, Function<Object, Boolean>> {
        @Override
        public Function<Object, Boolean> apply(Object value) {
            return new Equals1(value);
        }
        private class Equals1 implements Function<Object, Boolean> {
            Object x;
            Equals1(Object x) {
                this.x = x;
            }
            @Override
            public Boolean apply(Object value) {
                return x.equals(value);
            }
        }
    }

    public static BinFunction<Integer, Integer, Integer> multi = Functions.toBinFunction(new MultiplyI());
    private static class MultiplyI implements Function<Integer, Function<Integer, Integer>> {
        @Override
        public Function<Integer, Integer> apply(Integer value) {
            return new MultiplyI1(value);
        }
        private class MultiplyI1 implements Function<Integer, Integer> {
            Integer x;
            MultiplyI1(Integer x) {
                this.x = x;
            }
            @Override
            public Integer apply(Integer value) {
                return x * value;
            }
        }
    }

    public static BinFunction<Double, Double, Double> multD = Functions.toBinFunction(new MultiplyD());
    private static class MultiplyD implements Function<Double, Function<Double, Double>> {
        @Override
        public Function<Double, Double> apply(Double value) {
            return new MultiplyD1(value);
        }
        private class MultiplyD1 implements Function<Double, Double> {
            Double x;
            MultiplyD1(Double x) {
                this.x = x;
            }
            @Override
            public Double apply(Double value) {
                return x * value;
            }
        }
    }

    public static BinFunction<Number, Number, Number> mult = Functions.toBinFunction(new MultiplyN());
    private static class MultiplyN implements Function<Number, Function<Number, Number>> {
        @Override
        public Function<Number, Number> apply(Number value) {
            return new MultiplyN1(value);
        }
        private class MultiplyN1 implements Function<Number, Number> {
            Number x;
            MultiplyN1(Number x) {
                this.x = x;
            }
            @Override
            public Number apply(Number value) {
                return (Number)(x.doubleValue() * value.doubleValue());
            }
        }
    }

    public static BinFunction<Integer, Integer, Integer> addi = Functions.toBinFunction(new AddI());
    private static class AddI implements Function<Integer, Function<Integer, Integer>> {
        @Override
        public Function<Integer, Integer> apply(Integer value) {
            return new AddI1(value);
        }
        private class AddI1 implements Function<Integer, Integer> {
            Integer x;
            AddI1(Integer x) {
                this.x = x;
            }
            @Override
            public Integer apply(Integer value) {
                return x + value;
            }
        }
    }

    public static BinFunction<Number, Number, Number> add = Functions.toBinFunction(new AddN());
    private static class AddN implements Function<Number, Function<Number, Number>> {
        @Override
        public Function<Number, Number> apply(Number value) {
            return new Add1(value);
        }
        private class Add1 implements Function<Number, Number> {
            Number x;
            Add1(Number x) {
                this.x = x;
            }
            @Override
            public Number apply(Number value) {
                return (Number)(x.doubleValue() + value.doubleValue());
            }
        }
    }

    public static <X> Predicate<X> toPredicate(Function<X, Boolean> f) {
        return new ToPredicate<X>(f);
    }
    private static class ToPredicate<X> implements Predicate<X> {
        Function<X, Boolean> f;
        ToPredicate(Function<X, Boolean> foo){
            this.f = foo;
        }
        public boolean apply(X x) {
            return f.apply(x);
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