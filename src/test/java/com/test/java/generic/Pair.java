package com.test.java.generic;

import lombok.Data;

@Data
public class Pair<T> {
    private T first;
    private T second;
}
