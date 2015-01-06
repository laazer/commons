package com.laazer.common.functions;

import com.google.common.base.Function;

public interface BinFunction<X, Y, Z> {
    public Z apply(X value1, Y value2);
    public Function<Y, Z> toUniFun(X value);
}

