package com.laazer.common.functions;

import static org.junit.Assert.*;
import java.util.List;
import org.junit.Test;
import com.laazer.common.functions.Functions;
import com.laazer.common.collections.ListUtils;

public class FunctionSpec {

    @Test
    public void testUniFunctions() {
        assertTrue("test to boolean, true", Functions.toBoolean.apply("true"));
        assertTrue("test to boolean, false", !Functions.toBoolean.apply("false"));
       
    }
    
    @Test 
    public void testBinFunctions() {
        assertTrue("add 1 and 2", Functions.add.apply(1, 2).equals(3));
    }
}
