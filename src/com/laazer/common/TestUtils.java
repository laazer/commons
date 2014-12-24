package com.laazer.common;

import static org.junit.Assert.*;

public class TestUtils {

    public static void assertException(Boolean test, Exception thrown) {
        assertException(test, thrown, "");
    }

    public static void assertException(Boolean test, Exception thrown, String msg) {
        try {
            assert test;
            assertTrue(msg, false);
        }
        catch (Exception caught) {
            assertTrue(msg, caught.toString().contains(thrown.toString()));
        }
    }

    public static void assertException(Boolean test, String thrown) {
        assertException(test, thrown, "");
    }

    public static void assertException(Boolean test, String thrown, String msg) {
        try {
            assert test;
            assertTrue(msg, false);
        }
        catch (Exception caught) {
            assertTrue(msg, caught.toString().contains(thrown));
        }
    }
}
