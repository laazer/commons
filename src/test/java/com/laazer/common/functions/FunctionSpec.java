package com.laazer.common.functions;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import com.google.common.base.Function;
import com.google.common.base.Predicate;
import org.junit.Test;
import com.laazer.common.functions.Functions;

public class FunctionSpec {

    private Function<Object, Boolean> eq1;
    private Function<Object, Boolean> eq2;


    private void init(){
        eq1 =  Functions.equals.toUniFun("abc");
        eq2 = Functions.toUniFunction(Functions.equals, "abc");
    }

    @Test
    public void testUniFunctions() {
        assertTrue("test to boolean, true", Functions.toBoolean.apply("true"));
        assertTrue("test to boolean, false", !Functions.toBoolean.apply("false"));
        assertEquals("test identity", Functions.identity.apply("abc"), "abc");
    }
    
    @Test 
    public void testBinFunctions() {
        assertTrue("add 1 and 2", Functions.add.apply(1, 2).equals(3));
        assertTrue("mult 2 and 3", Functions.mult.apply(2, 3).equals(6));
    }

    @Test
    public void testToUniFunction() {
        init();
        assertTrue("abc = abc, with Functions equal", Functions.equals.apply("abc", "abc"));
        assertTrue("abc = abc, with toUniFunction method", eq1.apply("abc"));
        assertTrue("abc = abc, with toUniFunction function", eq2.apply("abc"));
    }

    @Test
    public void testToPredicate() {
        init();
        Predicate<Object> eq3 = Functions.toPredicate(eq1);
        assertTrue("test toPredicate", eq1.apply("abc") && eq2.apply("abc")
                                       && eq3.apply("abc"));
    }

}
