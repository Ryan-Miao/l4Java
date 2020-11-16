package com.test.java.base;

import org.junit.Test;

/**
 * 相同为0  不同为1
 * @author Ryan Miao 01399596
 * @date 2020/11/16 17:39
 */
public class 异或 {

    /**
     * 按位异或
     */
    @Test
    public void testBase() {
        Integer a = 2;
        Integer b = 3;
        Integer c = a ^ b;
        System.out.println("a:" + Integer.toBinaryString(a));
        System.out.println("b:" + Integer.toBinaryString(b));
        System.out.println( "c:" + Integer.toBinaryString(c));
        // 10
        // 11
        //-----
        // 01  = 0*2 +1*2^0 = 1

    }
}
