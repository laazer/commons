package com.laazer.common;

public interface BinFunction <K1, K2, T> {
    public T apply(K1 value1, K2 value2);
}

