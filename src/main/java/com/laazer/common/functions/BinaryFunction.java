package com.laazer.common.functions;

public interface BinaryFunction<X, Y, Z> {
    public Z apply(X value1, Y value2);
    public Function<Y, Z> toUnaryFunction(X value);
    public Function<X, Function<Y, Z>> toFunction();
}

class CompoundFunction<X, Y, Z> implements BinaryFunction<X, Y, Z> {
    private Function<X, Function<Y, Z>> f;
    public CompoundFunction(Function<X, Function<Y, Z>> f) {
        this.f = f;
    }
    public Z apply(X value1, Y value2) {
        return f.apply(value1).apply(value2);
    }

    public Function<Y, Z> toUnaryFunction(X value) {
        return f.apply(value);
    }

    public Function<X, Function<Y, Z>> toFunction() {
        return this.f;
    }
}