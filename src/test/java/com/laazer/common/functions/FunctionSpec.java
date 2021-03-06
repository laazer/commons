package com.laazer.common.functions;

import com.laazer.common.primitives.IntUtils;
import com.laazer.common.primitives.StringUtils;

import static org.junit.Assert.*;
import org.junit.Test;

public class FunctionSpec {

    private Function<Object, Boolean> eq1;
    private Function<Object, Boolean> eq2;


    private void init(){
        eq1 =  Functions.equals().toUnaryFunction("abc");
        eq2 = Functions.toUnaryFunction(Functions.equals(), "abc");
    }

    @Test
    public void testUniFunctions() {
        assertTrue("test to boolean, true", Functions.toBoolean.apply("true"));
        assertTrue("test to boolean, false", !Functions.toBoolean.apply("false"));
        assertEquals("test identity", Functions.identity.apply("abc"), "abc");

        assertTrue("test to boolean, true", Functions.toBoolean().apply("true"));
        assertTrue("test to boolean, false", !Functions.toBoolean().apply("false"));
        assertEquals("test identity", Functions.identity().apply("abc"), "abc");
    }

    @Test
    public void testToUniFunction() {
        init();
        assertTrue("abc = abc, with Functions equal", Functions.equals.apply("abc", "abc"));
        assertTrue("abc = abc, with toUnaryFunction method", eq1.apply("abc"));
        assertTrue("abc = abc, with toUnaryFunction function", eq2.apply("abc"));

        init();
        assertTrue("abc = abc, with Functions equal", Functions.equals().apply("abc", "abc"));
    }

    @Test
    public void testToPredicate() {
        init();
        Predicate<Object> eq3 = Functions.toPredicate(eq1);
        assertTrue("test toPredicate", eq1.apply("abc") && eq2.apply("abc")
                                       && eq3.apply("abc"));
    }

    @Test
    public void testAdd() {
        assertEquals("test Number adder on ints", Functions.add.apply(1, 1).intValue(), 2);
        assertEquals("test Number adder on double", Functions.add.apply(1.1, 1.1).doubleValue(), 2.2, 0);
    }

    @Test
    public void testMult() {
        assertEquals("test Number mult on ints", Functions.mult.apply(1, 1).intValue(), 1);
        assertEquals("test Number mult on double", Functions.mult.apply(1.1, 1.1).doubleValue(), 1.21, 0.001);
    }

    @Test
    public void testComposeSimple() {
        Function<Integer, Integer> add10Mult10 = Functions.compose(IntUtils.add.toUnaryFunction(10), IntUtils.mult.toUnaryFunction(10));
        assertTrue("Test compound simple function 1", add10Mult10.apply(5) == (5 + 10) * 10);
    }

    @Test
    public void testComposeComplex() {
        BinaryFunction<Integer, Integer, String> addThenString =
                Functions.toBinFunction(Functions.compose(IntUtils.add, Functions.<Integer>toStringObject()));
        assertEquals("Test compose complex", addThenString.apply(12, 27), "39");
        assertEquals("Test compose complex", addThenString.apply(20, 11), "31");
    }

    @Test
    public void testCompoundFirstArg() {
        BinaryFunction<Object, Integer, Integer> objectAdd =
                Functions.toBinFunction(Functions.compound(IntUtils.toInt(), IntUtils.add));
        assertTrue("Test compounding functions 1", objectAdd.apply("1", 10) == Integer.parseInt("1") + 10);
        assertTrue("Test compounding functions 2", objectAdd.apply("157", 13) == Integer.parseInt("157") + 13);

    }

    @Test
    public void testCompoundSecondArg() {
        BinaryFunction<Integer, String, Integer> stringAdd =
                Functions.toBinFunction(Functions.compound(IntUtils.add, IntUtils.<String>toInt()));
        assertTrue("Test compounding functions 1", stringAdd.apply(10, "1") == Integer.parseInt("1") + 10);
        assertTrue("Test compounding functions 2", stringAdd.apply(13, "157") == Integer.parseInt("157") + 13);
    }
}
