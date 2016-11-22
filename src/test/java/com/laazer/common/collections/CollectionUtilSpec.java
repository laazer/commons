package com.laazer.common.collections;

import com.laazer.common.functions.Functions;
import com.laazer.common.primitives.ArrayUtils;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Laazer
 */
public class CollectionUtilSpec {

    List<Integer> loi1;

    public void init() {
        loi1 = ArrayUtils.toIntList(1, 2, 3, 4, 5);
    }

    @Test
    public void testAdd() {
        init();
        assertEquals("Test empty sum", CollectionUtils.sum(new ArrayList<Number>()).intValue(), 0);
        assertEquals("Test sum", CollectionUtils.sum(loi1).intValue(), 15);
    }

    @Test
    public void testProduct() {
        init();
        assertEquals("Test empty sum", CollectionUtils.product(new ArrayList<Number>()).intValue(), 0);
        assertEquals("Test sum", CollectionUtils.product(loi1).intValue(), 120);
    }

    @Test
    public void testFind() {
        init();
        assertTrue("Test find", CollectionUtils.find(Functions.toPredicate(new Functions.AFunction<Integer, Boolean>() {
            @Override
            public Boolean apply(Integer integer) {
                return integer > 4;
            }
        }
        ), loi1) == 5);
    }

}
