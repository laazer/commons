package com.laazer.common.util;

import static org.junit.Assert.*;

public class TestUtils {

    public static void assertException(Boolean test, Exception thrown) {
        assertException("", test, thrown);
    }

    public static void assertException(String msg, Boolean test, Exception thrown) {
        assertException(msg, test, thrown.toString());
    }

    public static void assertException(Boolean test, String thrown) {
        assertException("", test, thrown);
    }

    public static void assertException(String msg, Boolean test, String thrown) {
        try {
            assert test;
            assertTrue(msg, false);
        }
        catch (Exception caught) {
            assertTrue(msg, caught.toString().contains(thrown));
        }
    }
}
