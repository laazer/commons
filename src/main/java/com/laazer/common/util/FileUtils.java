package com.laazer.common.util;

import java.io.*;
import com.laazer.common.collections.Box;
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

        @Override
        public boolean hasNext() {
            return line != null;
        }

        @Override
        public String next() {
            try {
                String l = line;
                line = br.readLine();
                return l;
            } catch (IOException e) {
                return "";
            }
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("this method is not currently supported");
        }

        @Override
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
