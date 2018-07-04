package com.test.java.string;

import org.junit.Test;

/**
 * @author Ryan Miao at 2018-07-04 11:34
 **/
public class StringTest {

    @Test
    public void testSplit() {
        String str = "aa.bb";
        System.out.println(str.split("\\.")[0]);
    }

}
