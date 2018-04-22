package com.test.java.stream.collect;

import static java.util.stream.Collectors.partitioningBy;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import org.junit.Test;

/**自定义质数收集器.
 * 自定义质数
 * @author Ryan Miao
 * @date 18-4-22
 */
public class PrimeNumbersCollectorTest {


    @Test
    public void testCustomizedCollectorForPrime() {
        Map<Boolean, List<Integer>> rs = IntStream.rangeClosed(2, 100).boxed()
            .collect(new PrimeNumbersCollector());
        System.out.println(rs.get(true));
    }

    public void testCustormizedPrimeCollectorPerf() {
        Long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            customizedPrimeCollector(1_000_000);
            long duration = (System.nanoTime() - start) / 1_000_000;
            if (duration < fastest) {
                fastest = duration;
            }
        }
        System.out.println("fastest excutio done in " + fastest + " msecs");
    }

    /**
     * 17681(修改前).9442ms(修改后)
     */
    private void customizedPrimeCollector(int endInclusive) {
        Map<Boolean, List<Integer>> rs = IntStream.rangeClosed(2, endInclusive).boxed()
            .collect(new PrimeNumbersCollector());
        System.out.println(rs.get(false).size());
    }

    /**
     * 419ms, 921501 个.
     */
    private void partitionPrimes(int endInclusive) {
        Map<Boolean, List<Integer>> rs = IntStream.rangeClosed(2, endInclusive).boxed()
            .collect(partitioningBy(this::isPrime));
        System.out.println(rs.get(false).size());
    }

    private boolean isPrime(int candidate) {
        int candidateRoot = (int) Math.sqrt((double) candidate);
        return IntStream.rangeClosed(2, candidateRoot).noneMatch(i -> candidate % i == 0);
    }


}