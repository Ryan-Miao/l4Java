package com.test.java.guava;

import static org.junit.Assert.assertEquals;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.Test;

/**
 * @author Ryan Miao at 2018-08-13 14:52
 **/
public class ListsTest {

    @Test
    public void testListPartition() {
        List<Integer> source = IntStream.rangeClosed(1, 100).boxed().collect(Collectors.toList());
        List<List<Integer>> partition = Lists.partition(source, 20);
        assertEquals(5, partition.size());
        assertEquals(20, partition.get(0).size());
    }

}
