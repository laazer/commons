package com.laazer.common.collections;

import static org.junit.Assert.*;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import org.junit.Test;
import com.laazer.common.functions.Functions;
import com.laazer.common.collections.ListUtils;

import java.util.Arrays;
import java.util.List;

/**
 * Created by laazer on 1/20/15.
 */
public class ListUtilsSpec {

    private List<String> los1;
    private List<String> los2;
    private List<String> los3;
    private List<String> losb;
    private List<String> losi;
    private List<String> losd;
    private List<Boolean> lob;
    private List<Integer> loi;
    private List<Integer> loi2;
    private List<Integer> loi3;
    private List<Double> lod1;
    private List<Double> lod2;

    private void init() {
        los1 = Arrays.asList("a", "b", "c");
        los2 = Arrays.asList("d", "e", "f");
        los3 = Arrays.asList("a", "b", "c", "d", "e", "f");
        losb = Arrays.asList("true", "false", "true");
        lob = Arrays.asList(true, false, true);
        losi = Arrays.asList("1", "2", "3");
        losd = Arrays.asList("1.2", "2.12", "3.44");
        loi = Arrays.asList(1, 2, 3);
        loi2 = Arrays.asList(3, 4, 5);
        loi3 = Arrays.asList(1, 2, 3, 3, 4, 5);
        lod1 = Arrays.asList(1.2, 2.12, 3.44);
        lod2 = Arrays.asList(1.0, 2.0, 3.0);

    }

    @Test
    public void testAppend() {
        init();
        assertEquals("test append on list of strings",
                ListUtils.append.apply(los1, los2), los3);
        assertEquals("test append on list of ints",
                ListUtils.append.apply(loi, loi2), loi3);
    }

    @Test
    public void testMap() {
        init();
        assertEquals("test map on list of strings to list of ints",
                ListUtils.map(losi, Functions.toInt), loi);
        assertEquals("test map on list of booleans to list of strings",
                ListUtils.map(lob, Functions.toString), losb);
    }

    @Test
    public void testToIntList() {
        init();
        assertEquals("test to int list from strings", ListUtils.toIntList.apply(losi), loi);
        assertEquals("test to int list from doubles", ListUtils.toIntList.apply(lod1), loi);
    }

    @Test
    public void testToStringList() {
        init();
        assertEquals("test double list from ints", ListUtils.toDoubleList.apply(loi), lod2);
        assertEquals("test double list from strings", ListUtils.toStringList.apply(lob), losb);
    }

    @Test
    public void testToDoubleList() {
        init();
        assertEquals("test double list from strings", ListUtils.toDoubleList.apply(losd), lod1);
        assertEquals("test double list from ints", ListUtils.toDoubleList.apply(loi), lod2);
    }
}
