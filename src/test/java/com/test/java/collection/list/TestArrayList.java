package com.test.java.collection.list;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

/**
 * @author ryan
 * @date 19-11-4 下午7:25
 */
public class TestArrayList {

    @Test
    public void testArrList() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            list.add(i);
        }

        list.size();

        list.get(2);

        list.remove(1);

    }

}
