package com.test.java.generic;

public class ArrayAlg {

    public static <T> T getMiddle(T... a) {
        return a[a.length / 2];
    }

    public static <U> U test() {
        return null;
    }

    public static void main(String[] args) {
        test();
    }
}
