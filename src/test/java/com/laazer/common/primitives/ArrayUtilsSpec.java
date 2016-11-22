package com.laazer.common.primitives;

import static org.junit.Assert.*;
import org.junit.Test;
import static com.laazer.common.primitives.ArrayUtils.*;
import static java.util.Collections.EMPTY_LIST;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Laazer
 */
public class ArrayUtilsSpec {

    private int[] ia;
    private List<Integer> il;
    private short[] sa;
    private List<Short> sl;
    private long[] la;
    private List<Long> ll;
    private float[] fa;
    private List<Float> fl;
    private double[] da;
    private List<Double> dl;
    private char[] ca;
    private List<Character> cl;
    private byte[] ba;
    private List<Byte> bl;
    private boolean[] bla;
    private List<Boolean> bll;

    private void initEmpty() {
        ia = new int[0];
        sa = new short[0];
        la = new long[0];
        fa = new float[0];
        da = new double[0];
        ca = new char[0];
        ba = new byte[0];
        bla = new boolean[0];
    }

    private void initFull() {
        ia = new int[3]; ia[0] = 1; ia[1] = 2; ia[2] = 3;
        sa = new short[3]; sa[0] = 1; sa[1] = 2; sa[2] = 3;
        la = new long[3]; la[0] = 1; la[1] = 2; la[2] = 3;
        fa = new float[3]; fa[0] = 1; fa[1] = 2; fa[2] = 3;
        da = new double[3]; da[0] = 1; da[1] = 2; da[2] = 3;
        ca = new char[3]; ca[0] = 1; ca[1] = 2; ca[2] = 3;
        ba = new byte[3]; ba[0] = 1; ba[1] = 2; ba[2] = 3;
        bla = new boolean[3]; bla[0] = true; bla[1] = false; bla[2] = true;
        il = new ArrayList<Integer>(); il.add(1); il.add(2); il.add(3);
        sl = new ArrayList<Short>(); sl.add((short)1); sl.add((short)2); sl.add((short)3);
        ll = new ArrayList<Long>(); ll.add((long)1); ll.add((long)2); ll.add((long)3);
        fl = new ArrayList<Float>(); fl.add((float)1); fl.add((float)2); fl.add((float)3);
        dl = new ArrayList<Double>(); dl.add((double)1); dl.add((double)2); dl.add((double)3);
        cl = new ArrayList<Character>(); cl.add((char)1); cl.add((char)2); cl.add((char)3);
        bl = new ArrayList<Byte>(); bl.add((byte)1); bl.add((byte)2); bl.add((byte)3);
        bll = new ArrayList<Boolean>(); bll.add(true); bll.add(false); bll.add(true);
    }

    @Test
    public void testToListEmpties() {
        initEmpty();
        assertEquals(toIntList(ia), EMPTY_LIST);
        assertEquals(toShortList(sa), EMPTY_LIST);
        assertEquals(toLongList(la), EMPTY_LIST);
        assertEquals(toFloatList(fa), EMPTY_LIST);
        assertEquals(toDoubleList(da), EMPTY_LIST);
        assertEquals(toCharList(ca), EMPTY_LIST);
        assertEquals(toByteList(ba), EMPTY_LIST);
        assertEquals(toBoolList(bla), EMPTY_LIST);
    }

    @Test
    public void testToArray() {
        initFull();
        assertArrayEquals(toIntArray(il), ia);
        assertArrayEquals(toShortArray(sl), sa);
        assertArrayEquals(toLongArray(ll), la);
        assertTrue(Arrays.equals(toFloatArray(fl), fa));
        assertTrue(Arrays.equals(toDoubleArray(dl), da));
        assertTrue(Arrays.equals(toCharArray(cl), ca));
        assertTrue(Arrays.equals(toByteArray(bl), ba));
        assertTrue(Arrays.equals(toBoolArray(bll), bla));
    }


}
