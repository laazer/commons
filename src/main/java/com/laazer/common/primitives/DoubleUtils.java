package com.laazer.common.primitives;

import com.laazer.common.functions.BinaryFunction;
import com.laazer.common.functions.Function;
import com.laazer.common.functions.Functions;

/**
 * Created by Dexter
 */
public class DoubleUtils {

    public final static <T> Function<T, Double> toDouble() {
        return new ToDouble<T>();
    }
    private static class ToDouble<T> implements Function<T, Double> {

        public Double apply(T value) {
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

    public final static BinaryFunction<Double, Double, Double> mult = Functions.toBinFunction(new Multiply());
    private static class Multiply implements Function<Double, Function<Double, Double>> {

        public Function<Double, Double> apply(Double value) {
            return new Multiply1(value);
        }
        private final class Multiply1 implements Function<Double, Double> {
            Double x;
            Multiply1(Double x) {
                this.x = x;
            }

            public Double apply(Double value) {
                return x * value;
            }
        }
    }
}
