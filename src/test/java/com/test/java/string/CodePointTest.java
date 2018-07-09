package com.test.java.string;

import java.util.function.IntConsumer;
import org.junit.Test;

/**
 * @author Ryan Miao at 2018-07-06 15:28
 **/
public class CodePointTest {

    @Test
    public void testCodePoint() {
        String a = "\uD835\uDD46㋛Hello";
        System.out.println(a);
        System.out.println(a.length());
        a.codePoints().forEach(value -> System.out.println((char) value));
    }

}
