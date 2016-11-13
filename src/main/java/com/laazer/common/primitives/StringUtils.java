package com.laazer.common.primitives;

import com.laazer.common.functions.BinaryFunction;
import com.laazer.common.functions.Function;
import com.laazer.common.functions.Functions;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
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
    public final static String shortenString(String s, int length) {
        return shortenString(s, length, Digest.HEX);
    }

    /**
     * Creates a unique shortened {@code String} hash
     * @param s given {@code String}
     * @param length length of shortened {@code String}
     * @param  digest type of {@code String} output
     * @return a unique shortened {@code String} hash
     */
    public final static String shortenString(String s, int length, Digest digest) {
        if (s.length() <= length) return s;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(s.getBytes(StandardCharsets.UTF_8));
            switch (digest) {
                case HEX: return toHex(messageDigest.digest()).substring(0, length);
                case BYTE_STRING: return new String(messageDigest.digest(),
                        StandardCharsets.UTF_8).substring(0, length);
                default: return toHex(messageDigest.digest()).substring(0, length);
            }
        } catch (NoSuchAlgorithmException nsa) {
            return s;
        }
    }

    public final static String toHex(byte[] bytes) {
        BigInteger bi = new BigInteger(1, bytes);
        return String.format("%0" + (bytes.length << 1) + "X", bi);
    }

    public enum Digest {
        HEX,
        BYTE_STRING;
    }

    public static final BinaryFunction<String, String, String> append(String separator) {
        return Functions.toBinFunction(new Append(separator));
    }

    public static final BinaryFunction<String, String, String> append() {
        return append("");
    }

    private static class Append implements Function<String, Function<String, String>> {
        String separator;
        Append(String separator) {
            this.separator = separator;
        }
        public Function<String, String> apply(String input) {
            return new Append1(input, separator);
        }

        private final class Append1 implements Function<String, String> {
            String s;
            String sep;
            Append1(String s, String sep) {
                this.s = s;
                this.sep = sep;
            }
            public String apply(String input) {
                return this.s + sep + input;
            }
        }
    }
}
