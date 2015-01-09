package com.laazer.common.util;

import com.google.common.base.Function;
import com.laazer.common.collections.ListUtils;
import com.laazer.common.functions.BinFunction;
import com.laazer.common.functions.Functions;
import com.sun.beans.editors.NumberEditor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Intern on 8/17/2014.
 */
public class TimeTest  {
    private static long createdAt;
    private static Map<String, List<Long>> map = new HashMap<String, List<Long>>();
    private static final Double MILLI_MULT = (Math.pow(10, -6));
    private static final Double SEC_MULT = (Math.pow(10, -9));

    public static void start() {
        createdAt = System.nanoTime();
    }

    public synchronized static void addMarker(String marker) {
        map.put(marker, new ArrayList());
    }

    public synchronized static long mark(String marker) {
        long res = System.nanoTime() - createdAt;
        map.get(marker).add(res);
        return res;
    }

    public static List<Long> getTimes(String marker) {
        return map.get(marker);
    }

    public static Long getAvgTime(String marker) {
        Long total = 0L;
        for(Long l : map.get(marker)) {
            total += l;
        }
        return total/map.get(marker).size();
    }

    private static class MultMili implements Function<Long, Double> {
        public Double apply(Long x) {
            return x * MILLI_MULT;
        }
    }

    private static class MultSec implements Function<Long, Double> {
        public Double apply(Long x) {
            return x * SEC_MULT;
        }
    }

    public static List<Double> getTimesInMilli(String marker) {
        return ListUtils.map(getTimes(marker), new MultMili());
    }

    public static Double getAvgMilliTime(String marker) {
        return getAvgTime(marker) * MILLI_MULT;
    }

    public static List<Double> getTimesInSec(String marker) {
        return ListUtils.map(getTimes(marker), new MultSec());
    }

    public static Double getAvgSecTime(String marker) {
        return getAvgTime(marker) * SEC_MULT;
    }

    public static void printAvgTime(String marker) {
        System.out.printf("%s: %f \n", marker, getAvgTime(marker));
    }

    public static void printAvgMilliTime(String marker) {
        System.out.printf("%s: %f \n", marker, getAvgMilliTime(marker));
    }

    public static void printAvgSecTime(String marker) {
        System.out.printf("%s: %f \n", marker, getAvgSecTime(marker));
    }

    public static void resetStartTime() {
        createdAt = System.nanoTime();
    }

    public static void printAllAvgTimes() {
        for (String key : map.keySet()) {
            printAvgMilliTime(key);
        }
    }

    public static void totalReset() {
        createdAt = System.nanoTime();
        map.clear();
    }
}
