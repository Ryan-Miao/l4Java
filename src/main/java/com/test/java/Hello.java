package com.test.java;

public class Hello {

    public static void main(String[] args) {
        StringBuilder name = new StringBuilder("World");
        if (args != null && args.length > 0) {
            for (String arg : args) {
                name.append(" ").append(arg);
            }
        }
        System.out.println("Hello " + name);
    }
}
