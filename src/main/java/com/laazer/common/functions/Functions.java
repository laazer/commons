package com.laazer.common.functions;

/**
 * Class containing common functions
 * @author Jacob
 *
 */
public class Functions {

    public final static Function<Object, Object> identity = new Identity();
    private static class Identity<X> implements Function<X, X> {
        public X apply(X value) {
            return value;
        }
    }

    public final static Function<Object, String> toString = new ToString();
    private static class ToString implements Function<Object, String> {
        public String apply(Object value) {
            return value.toString();
        }
    }

    public final static Function<Object, Integer> getHashCode = new GetHashCode();
    private static class GetHashCode implements Function<Object, Integer> {
        public Integer apply(Object value) {
            return toInt.apply(value.hashCode());
        }
    }
    
    public final static Function<Object, Double> toDouble = new ToDouble();
    private static class ToDouble implements Function<Object, Double> {
        
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
    
    public final static Function<Object, Integer> toInt = new ToInt();
    private static class ToInt implements Function<Object, Integer> {
        
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
    
    public final static Function<Object, Boolean> toBoolean = new ToBoolean();
    private static class ToBoolean implements Function<Object, Boolean> {
        
        public Boolean apply(Object value) {
            return Boolean.parseBoolean(value.toString());
        }
    }

    /**
     * Transforms a {@code Function} into a {@code BinaryFunction}
     * @param function a given {@code Function}
     * @param <X> first input value type
     * @param <Y> second input value type
     * @param <Z> function return type
     * @return a {@code BinaryFunction} representation of the given {@code Function}
     */
    public final static <X, Y, Z> BinaryFunction<X, Y, Z> toBinFunction(Function<X, Function<Y, Z>> function) {
        return new CompoundFunction(function);
    }

    public final static <X, Y, Z> Function<Y, Z> toUnaryFunction(BinaryFunction<X, Y, Z> function, X value) {
        return function.toUnaryFunction(value);
    }

    public final static BinaryFunction<Object, Object, Boolean> equals = Functions.toBinFunction(new Equals());
    private static class Equals implements Function<Object, Function<Object, Boolean>> {
        
        public Function<Object, Boolean> apply(Object value) {
            return new Equals1(value);
        }
        private final class Equals1 implements Function<Object, Boolean> {
            Object x;
            Equals1(Object x) {
                this.x = x;
            }
            
            public Boolean apply(Object value) {
                return x.equals(value);
            }
        }
    }

    public final static BinaryFunction<Integer, Integer, Integer> multi = Functions.toBinFunction(new MultiplyI());
    private static class MultiplyI implements Function<Integer, Function<Integer, Integer>> {
        
        public Function<Integer, Integer> apply(Integer value) {
            return new MultiplyI1(value);
        }
        private final class MultiplyI1 implements Function<Integer, Integer> {
            Integer x;
            MultiplyI1(Integer x) {
                this.x = x;
            }
            
            public Integer apply(Integer value) {
                return x * value;
            }
        }
    }

    public final static BinaryFunction<Double, Double, Double> multD = Functions.toBinFunction(new MultiplyD());
    private static class MultiplyD implements Function<Double, Function<Double, Double>> {
        
        public Function<Double, Double> apply(Double value) {
            return new MultiplyD1(value);
        }
        private final class MultiplyD1 implements Function<Double, Double> {
            Double x;
            MultiplyD1(Double x) {
                this.x = x;
            }
            
            public Double apply(Double value) {
                return x * value;
            }
        }
    }

    public final static BinaryFunction<Number, Number, Number> mult = Functions.toBinFunction(new MultiplyN());
    private static class MultiplyN implements Function<Number, Function<Number, Number>> {
        
        public Function<Number, Number> apply(Number value) {
            return new MultiplyN1(value);
        }
        private final class MultiplyN1 implements Function<Number, Number> {
            Number x;
            MultiplyN1(Number x) {
                this.x = x;
            }
            
            public Number apply(Number value) {
                return (Number)(x.doubleValue() * value.doubleValue());
            }
        }
    }

    public final static BinaryFunction<Integer, Integer, Integer> addi = Functions.toBinFunction(new AddI());
    private static class AddI implements Function<Integer, Function<Integer, Integer>> {
        
        public Function<Integer, Integer> apply(Integer value) {
            return new AddI1(value);
        }
        private final class AddI1 implements Function<Integer, Integer> {
            Integer x;
            AddI1(Integer x) {
                this.x = x;
            }
            
            public Integer apply(Integer value) {
                return x + value;
            }
        }
    }

    public final static BinaryFunction<Number, Number, Number> add = Functions.toBinFunction(new AddN());
    private static class AddN implements Function<Number, Function<Number, Number>> {
        
        public Function<Number, Number> apply(Number value) {
            return new Add1(value);
        }
        private final class Add1 implements Function<Number, Number> {
            Number x;
            Add1(Number x) {
                this.x = x;
            }
            
            public Number apply(Number value) {
                return (Number)(x.doubleValue() + value.doubleValue());
            }
        }
    }

    public final static <X> Predicate<X> toPredicate(Function<X, Boolean> f) {
        return new ToPredicate<X>(f);
    }
    private static class ToPredicate<X> implements Predicate<X> {
        Function<X, Boolean> f;
        ToPredicate(Function<X, Boolean> foo){
            this.f = foo;
        }
        public Boolean apply(X x) {
            return f.apply(x);
        }
    }

    public static abstract class AFunction<X, Y> implements Function<X, Y> {
        public abstract Y apply(X x);
    }

    public final static <X, Y, Z> Function<X, Z> compound(final Function<X, ? extends Y> f1,
                                                          final Function<Y, Z> f2) {
        return new AFunction<X, Z>() {
            @Override
            public Z apply(X x) {
                return f2.apply(f1.apply(x));
            }
        };
    }

    public final static <W, X, Y, Z> Function<W, Function<X, Z>> compound(final BinaryFunction<W, X, ? extends Y> f1,
                                                          final Function<Y, Z> f2) {
        return new AFunction<W, Function<X, Z>>() {
            @Override
            public Function<X, Z> apply(final W w) {
                return new AFunction<X, Z>() {
                    @Override
                    public Z apply(X x) {
                        return f2.apply(f1.apply(w, x));
                    }
                };
            }
        };
    }

    public final static <W, X, Y, Z> Function<W, Function<Y, Z>> compound(final Function<W, ? extends X> f1,
                                                                          final BinaryFunction<X, Y, Z> f2) {
        return new AFunction<W, Function<Y, Z>>() {
            @Override
            public Function<Y, Z> apply(final W w) {
                return new AFunction<Y, Z>() {
                    @Override
                    public Z apply(Y y) {
                        return f2.apply(f1.apply(w), y);
                    }
                };
            }
        };
    }
}

