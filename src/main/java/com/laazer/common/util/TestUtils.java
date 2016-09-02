package com.laazer.common.util;

import static org.junit.Assert.*;

public class TestUtils {

    public final static void assertException(Boolean test, Exception thrown) {
        assertException("", test, thrown);
    }

    public final static void assertException(String msg, Boolean test, Exception thrown) {
        assertException(msg, test, thrown.toString());
    }

    public final static void assertException(Boolean test, String thrown) {
        assertException("", test, thrown);
    }

    public final static void assertException(String msg, Boolean test, String thrown) {
        try {
            assert test;
            assertTrue(msg, false);
        }
        catch (Exception caught) {
            assertTrue(msg, caught.toString().contains(thrown));
        }
    }
}
