package com.laazer.common.primitives;

/**
 * Created by Dexter on 7/6/2016.
 */
public class IntUtils {

    public final static int pow(int n, int e) {
        int result = 1;
        while (e > 0) {
            result *= n;
            e--;
        }
        return result;
    }

    public final static long bigPow(int n, int e) {
        long result = 1;
        while (e > 0) {
            result *= n;
            e--;
        }
        return result;
    }

}
