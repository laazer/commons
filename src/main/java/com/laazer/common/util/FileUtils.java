package com.laazer.common.util;

import java.io.*;
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

        public LineIterable(String path) {
            this(new File(path));
        }

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
        public void remove() {
            throw new UnsupportedOperationException("this method is not currently supported");
        }

        public Iterator<String> iterator() {
            return this;
        }
    }

}
