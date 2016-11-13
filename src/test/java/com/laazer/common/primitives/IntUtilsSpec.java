package com.laazer.common.primitives;

import static com.laazer.common.primitives.IntUtils.*;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by Dexter
 */
public class IntUtilsSpec {


    @Test
    public void testPow() {
        assertEquals("Test pow 2^3", pow(2, 3), 8);
        assertEquals("Test pow 1^big num", pow(1, 131903), 1);
        assertEquals("Test pow -1^big odd num", pow(-1, 131903), -1);
        assertEquals("Test negative pow", pow(-4, 3), -64);
        assertEquals("Test pow 0 as n", pow(0, 100), 0);
        assertEquals("Test pow 0 as e", pow(100232324, 0), 1);
    }

    @Test
    public void testBigPow() {
        assertEquals("Test pow 0 as n", bigPow(0, 100), 0L);
        assertEquals("Test pow 0 as e", bigPow(100232324, 0), 1L);
        assertEquals("Test pow 1^big num", bigPow(1, 131903), 1L);
        assertEquals("Test pow -1^big odd num", bigPow(-1, 131903), -1L);
        assertEquals("Test big number", bigPow(2, 33), 8589934592L);
    }

    @Test
    public void testFunctionObjects() {
        assertEquals("Test add object", (int)add.apply(10, 21), 31);
        assertEquals("Test mult object", (int)mult.apply(10, 5), 50);
        assertEquals("Test 1 toInt object", (int)toInt().apply("101"), 101);
        assertEquals("Test 2 toInt object", (int)toInt().apply(51.1), 51);
    }
}
