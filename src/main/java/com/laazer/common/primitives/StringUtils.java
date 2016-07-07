package com.laazer.common.primitives;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Jacob on 6/16/2016.
 */
public class StringUtils {

    /**
     * Creates a unique shortened {@code String} hash
     * @param s given {@code String}
     * @param length length of shortened {@code String}
     * @return a unique shortened {@code String} hash
     */
    public static String shortenString(String s, int length) {
        if (s.length() <= length) return s;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(s.getBytes());
            return toHex(messageDigest.digest()).substring(0, length);
        } catch (NoSuchAlgorithmException nsa) {
            return s;
        }
    }

    public static String toHex(byte[] bytes) {
        BigInteger bi = new BigInteger(1, bytes);
        return String.format("%0" + (bytes.length << 1) + "X", bi);
    }
}
