package com.laazer.common.primitives;

import com.laazer.common.functions.BinaryFunction;
import com.laazer.common.functions.Function;
import com.laazer.common.functions.Functions;

/**
 * Created by Dexter on 7/6/2016.
 */
public class IntUtils {

    public final static int pow(int n, int e) {
        return (int)bigPow(n, e);
    }

    public final static long bigPow(int n, int e) {
        assert e > -1;
        long result = 1;
        while (e > 0) {
            result *= n;
            e--;
        }
        return result;
    }

    public final static <T> Function<T, Integer> toInt() {
        return new ToInt<T>();
    }
    private static class ToInt<T> implements Function<T, Integer> {

        public Integer apply(T value) {
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

    public final static BinaryFunction<Integer, Integer, Integer> mult = Functions.toBinFunction(new Multiply());
    private static class Multiply implements Function<Integer, Function<Integer, Integer>> {

        public Function<Integer, Integer> apply(Integer value) {
            return new Multiply1(value);
        }
        private final class Multiply1 implements Function<Integer, Integer> {
            Integer x;
            Multiply1(Integer x) {
                this.x = x;
            }

            public Integer apply(Integer value) {
                return x * value;
            }
        }
    }

    public final static BinaryFunction<Integer, Integer, Integer> add = Functions.toBinFunction(new Add());
    private static class Add implements Function<Integer, Function<Integer, Integer>> {

        public Function<Integer, Integer> apply(Integer value) {
            return new Add.Add1(value);
        }
        private final class Add1 implements Function<Integer, Integer> {
            Integer x;
            Add1(Integer x) {
                this.x = x;
            }

            public Integer apply(Integer value) {
                return x + value;
            }
        }
    }

}
