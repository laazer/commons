package com.laazer.common;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

/**
 * Created by jacob on 8/28/14.
 */
public class FileUtils {

    public Iterator<String> genLineIterator(String filePath) {
        return genLineIterator(new File(filePath));
    }

    public Iterator<String> genLineIterator(File file) {
        return new LineIterable(file);
    }

    public Iterable<String> genLineIterable(String filePath) {
        return genLineIterable(new File(filePath));
    }

    public Iterable<String> genLineIterable(File file) {
        return new LineIterable(file);
    }

    private class LineIterable implements Iterator<String>, Iterable<String> {

        BufferedReader br;
        String line;

        public LineIterable(File file) {
            try {
                br = new BufferedReader(new FileReader(file));
                line = br.readLine();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException io) {
                io.printStackTrace();
            }
        }

        public boolean hasNext() {
            return line != null;
        }

        public String next() {
            try {
                String l = line;
                line = br.readLine();
                return l;
            } catch (IOException e) {
                return "";
            }
        }

        public Iterator<String> iterator() {
            return this;
        }
    }

    public static String readFile(String path, Charset encoding)
            throws IOException
    {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

    public static Box<String> safeReadFile(String path, Charset encoding) {
        try {
            byte[] encoded = Files.readAllBytes(Paths.get(path));
            return Box.fill(new String(encoded, encoding));
        } catch (IOException io) {
            return Box.EMPTY;
        }
    }
}
