package com.test.java.collection;

import java.util.ArrayDeque;
import org.junit.Test;

/**
 * @author ryan
 * @date 19-10-23 下午5:26
 */
public class TestArrayDeque {

    @Test
    public void testArrDeque() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        deque.add(1);
        deque.add(2);
        deque.add(3);
        deque.addFirst(4);
        deque.addFirst(5);
        System.out.println(deque);
        System.out.println(deque.poll());
        System.out.println(deque.pop());
    }

}
