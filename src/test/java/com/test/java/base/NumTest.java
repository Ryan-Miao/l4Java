package com.test.java.base;

import org.junit.Test;

/**
 * @author Ryan Miao at 2018-07-06 13:50
 **/
public class NumTest {

    @Test
    public void numTest() {
        int x = 1;
        x += 3.5;
        System.out.println(x);

        System.out.println(1 << 35);
    }

    @Test
    public void testBinaryStr() {
        // int是32位的
        Integer a = Integer.MAX_VALUE;
        printBinaryStr(a);
        printBinaryStr(Integer.MIN_VALUE);
    }

    private void printBinaryStr(Integer a) {
        String str = Integer.toBinaryString(a);
        System.out.println(a + "[binaryStr]:" + str);
        System.out.println(a +"[binaryStrlen]:"+str.length());
    }

}
