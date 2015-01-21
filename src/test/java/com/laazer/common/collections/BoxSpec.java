package com.laazer.common.collections;

import static org.junit.Assert.*;
import com.laazer.common.util.TestUtils;
import com.laazer.common.functions.Functions;
import org.junit.Test;


/**
 * Test Class for Box
 */
public class BoxSpec {

    private Box<String> stringBox;
    private Box<String> stringBox2;
    private Box<String> stringBox3;
    private Box<Integer> integerBox;
    private Box<Integer> integerBox2;
    private Box<Integer> integerBox3;
    private Box<String>  empty1;
    private Box<Integer> empty2;
    private Box<Object> empty3;
    private Box<String> batbox;

    public void init() {
        stringBox = Box.fill("abc");
        stringBox2 = Box.fill("abc");
        stringBox3 = Box.fill("random");
        integerBox = Box.fill(1);
        integerBox2 = Box.fill(1);
        integerBox3 = Box.fill(3);
        batbox = Box.fill("Bat Man!");
        empty1 = Box.EMPTY;
        empty2 = Box.EMPTY;
        empty3 = Box.EMPTY;
    }

    @Test
    public void testEquals() {
        init();
        assertTrue("empty equals empty", empty1.equals(empty2));
        assertTrue("empty equals empty", empty2.equals(empty1));
        assertTrue("box containing same contests are equal", stringBox.equals(stringBox2));
        assertTrue("box containing diff contests are equal", !stringBox.equals(stringBox3));
        assertTrue("box containing same contests are equal", integerBox.equals(integerBox2));
        assertTrue("box containing diff contests are equal", !integerBox.equals(integerBox3));
    }

    @Test
    public void testToString() {
        init();
        assertTrue("empty box toString", empty1.toString().equals("Box[]"));
        assertTrue("full string box toString", stringBox.toString().equals("Box[abc]"));
        assertTrue("full int box toString", integerBox.toString().equals("Box[1]"));
    }

    @Test
    public void testMap() {
        init();
        assertEquals("empty box map", empty3.map(Functions.toString), empty3);
        assertEquals("full int box map", integerBox.map(Functions.toString), Box.fill("1"));
        assertEquals("full string box map", Box.fill("true").map(Functions.toBoolean), Box.fill(true));
    }

    @Test
    public void testIsEmpty() {
        init();
        assertTrue("empty box is empty", empty1.isEmpty());
        assertFalse("full box is not empty", batbox.isEmpty());
    }

    @Test
    public void testIsFull() {
        init();
        assertFalse("empty box is not full", empty1.isFull());
        assertTrue("full box is full", batbox.isFull());
    }

    @Test
    public void testGet() {
        init();
        assertEquals("get string from full string box", stringBox.get(), "abc");
        assertEquals("get int from full int box", integerBox.get(), new Integer(1));
        //TestUtils.assertException("exception on get with empty", empty1.get().equals("anything"), "Empty Box");
    }

    @Test
    public void testGetOrElse() {
        init();
        assertEquals("test getOrElse on empty string", empty1.getOrElse("abc"), "abc");
        assertEquals("test getOrElse on empty empty", empty2.getOrElse("abc"), "abc");
        assertEquals("test getOrElse on full string,", stringBox.getOrElse("abc"), "abc");
        assertEquals("test getOrElse on full int", integerBox.getOrElse("abc"), 1);
    }

    @Test
    public void testGetOrElseKeepType() {
        init();
        assertEquals("test getOrElseKeepType on string empty", empty1.getOrElseKeepType("abc"), "abc");
        assertEquals("test getOrElseKeepType on int empty", empty2.getOrElseKeepType(3), new Integer(3));
        assertEquals("test getOrElseKeepType on string full", stringBox.getOrElseKeepType("abc"), "abc");
        assertEquals("test getOrElseKeepType on int full", integerBox.getOrElseKeepType(3), new Integer(1));
    }

}
