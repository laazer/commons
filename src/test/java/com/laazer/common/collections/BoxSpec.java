package com.laazer.common.collections;

import static org.junit.Assert.*;
import com.laazer.common.util.TestUtils;
import com.laazer.common.functions.Functions;
import org.junit.Test;


/**
 * Test Class for Box
 */
public class BoxSpec {

    Box <String> stringBox;
    Box<String> stringBox2;
    Box<String> stringBox3;
    Box<Integer> integerBox;
    Box<Integer> integerBox2;
    Box<Integer> integerBox3;
    Box<String>  empty1;
    Box<Integer> empty2;
    Box<Object> empty3;

    public void init() {
        stringBox = Box.fill("abc");
        stringBox2 = Box.fill("abc");
        stringBox3 = Box.fill("random");
        integerBox = Box.fill(1);
        integerBox2 = Box.fill(1);
        integerBox3 = Box.fill(3);
        empty1 = Box.EMPTY;
        empty2 = Box.EMPTY;
        empty3 = Box.EMPTY;
    }

    @Test
    public void testEquals() {
        init();
        assertTrue(empty1.equals(empty2));
        assertTrue(empty2.equals(empty1));
        assertTrue(stringBox.equals(stringBox2));
        assertTrue(!stringBox.equals(stringBox3));
        assertTrue(integerBox.equals(integerBox2));
        assertTrue(!integerBox.equals(integerBox3));
    }

    @Test
    public void testToString() {
        init();
        assertTrue(empty1.toString().equals("Box[]"));
        assertTrue(stringBox.toString().equals("Box[abc]"));
        assertTrue(integerBox.toString().equals("Box[1]"));
    }

    @Test
    public void testMap() {
        init();
        assertEquals(empty3.map(Functions.toString), empty3);
        assertEquals(integerBox.map(Functions.toString), Box.fill("1"));
        assertEquals(Box.fill("true").map(Functions.toBoolean), Box.fill(true));
    }

    @Test
    public void testIsEmpty() {
        init();
        assertTrue(empty1.isEmpty());
        assertFalse(stringBox.isEmpty());
    }

    @Test
    public void testIsFull() {
        init();
        assertFalse(empty1.isFull());
        assertTrue(stringBox.isFull());
    }

    @Test
    public void testGet() {
        init();
        assertEquals(stringBox.get(), "abc");
        assertEquals(integerBox.get(), new Integer(1));
        TestUtils.assertException(empty1.isFull(), "Empty Box");
    }
}
