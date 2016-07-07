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
        return shortenString(s, length, Digest.HEX);
    }

    /**
     * Creates a unique shortened {@code String} hash
     * @param s given {@code String}
     * @param length length of shortened {@code String}
     * @param  digest type of {@code String} output
     * @return a unique shortened {@code String} hash
     */
    public static String shortenString(String s, int length, Digest digest) {
        if (s.length() <= length) return s;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(s.getBytes());
            switch (digest) {
                case HEX: return toHex(messageDigest.digest()).substring(0, length);
                case BYTE_STRING: return new String(messageDigest.digest()).substring(0, length);
                default: return toHex(messageDigest.digest()).substring(0, length);
            }
        } catch (NoSuchAlgorithmException nsa) {
            return s;
        }
    }

    public static String toHex(byte[] bytes) {
        BigInteger bi = new BigInteger(1, bytes);
        return String.format("%0" + (bytes.length << 1) + "X", bi);
    }

    public enum Digest {
        HEX,
        BYTE_STRING;
    }
}
