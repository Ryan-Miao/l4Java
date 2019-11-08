package com.test.java.guava;

import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnel;
import org.junit.Test;

/**
 * @author ryan
 * @date 19-8-28 下午7:35
 */
public class BloomFilterTest {

    @Test
    public void test() {

        int expectedInsertions = 1000000;

        final BloomFilter<String> bloomFilter = BloomFilter.create(
            (Funnel<String>) (from, into) -> into.putString(from, Charsets.UTF_8),
            expectedInsertions);

        for (int i = 0; i < 1000000; i++) {
            bloomFilter.put("test" + i);
        }

        int yes = 0;
        for (int i = 0; i < 1000000; i++) {
            boolean b = bloomFilter.mightContain("abc" + i);
            if (b) {
                yes++;
            }
        }

        System.out.println(yes);

    }

}
