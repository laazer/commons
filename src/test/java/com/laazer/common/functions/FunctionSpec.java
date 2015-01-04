package com.laazer.common.functions;

import static org.junit.Assert.*;
import java.util.List;
import org.junit.Test;
import com.laazer.common.functions.Functions;
import com.laazer.common.collections.ListUtils;

public class FunctionSpec {

    String s1 = "the quick brown fox yadda yadda";
    String s2 = "for the win";
    String s3 = "i have to pee";
    List<String> l1 = ListUtils.arrayToList(s1, s2, s3);  
    
    @Test
    public void testUniFunctions() {
        assertTrue(Functions.toBoolean.apply("true"));
        assertTrue(!Functions.toBoolean.apply("false"));
       
    }
    
    @Test 
    public void testBinFunctions() {
        assertTrue(Functions.add.apply(1, 2).equals(3));
    }
}
