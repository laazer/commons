package com.laazer.common;

public class TestUtils {
    
    public static Boolean assertException(Boolean test, Exception thrown) {
        try {
            return false;
        }
        catch (Exception caught) {
            return thrown.toString().equals(caught.toString());
        }
    }
}
