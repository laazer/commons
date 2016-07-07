package com.laazer.common.primitives;

import org.junit.Test;
import static org.junit.Assert.*;
import static com.laazer.common.primitives.StringUtils.*;

/**
 * Created by laazer on 7/7/2016.
 */
public class StringUtilsSpec {

    private String test1;
    private String res1;
    private String res12;
    private String test2;
    private String res2;
    private String res22;
    private String test3;
    private String res3;
    private String res32;

    private void init() {
        test1 = "for bar biz baz";
        res1 = "E13B06A72B";
        res12 = "�;\u0006�+M��t�";
        test2 = "the quick brown fox jumped over the fence";
        res2 = "D49A4F93D5B04";
        res22 ="ԚO�հO�Q�Ɲ!�`\u0019";
        test3 = "can't touch this, dun dun dun dun dun dun cant touch this 2324 343";
        res3 = "D49F33D534FD345E9";
        res32 = "ԟ3�4�4^�P�ospM���";
    }

    @Test
    public void testShortenStringHex() {
        init();
        assertEquals(shortenString(test1, 10), res1);
        assertNotEquals(shortenString(test1.substring(0, 9), 5), res1.substring(0, 5));
        assertEquals(shortenString(test2, 13) ,res2);
        assertNotEquals(shortenString(test2.substring(0, 13), 7), res2.substring(0, 7));
        assertEquals(shortenString(test3, 17), res3);
        assertNotEquals(shortenString(test3.substring(0, 11), 9), res3.substring(0, 9));
    }

    @Test
    public void testShortenStringByte() {
        init();
        assertEquals(shortenString(test1, 10, Digest.BYTE_STRING), res12);
        assertNotEquals(shortenString(test1.substring(0, 9), 5, Digest.BYTE_STRING), res12.substring(0, 5));
        assertEquals(shortenString(test2, 13, Digest.BYTE_STRING) ,res22);
        assertNotEquals(shortenString(test2.substring(0, 13), 7, Digest.BYTE_STRING), res22.substring(0, 7));
        assertEquals(shortenString(test3, 17, Digest.BYTE_STRING), res32);
        assertNotEquals(shortenString(test3.substring(0, 11), 9, Digest.BYTE_STRING), res32.substring(0, 9));
    }
}
