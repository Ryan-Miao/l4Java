package com.test.java.stream.collect;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * Created by Ryan Miao on 4/20/18.
 */
public class PrimeNumbersCollector implements
        Collector<Integer, Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> {

    @Override
    public Supplier<Map<Boolean, List<Integer>>> supplier() {
        Map<String, Integer> map = new HashMap<String, Integer>() {
            {
                //实例化代码块
            }
        };
        return () -> new HashMap<Boolean, List<Integer>>() {{
            put(true, new ArrayList<Integer>());
            put(false, new ArrayList<Integer>());
        }};
    }

    @Override
    public BiConsumer<Map<Boolean, List<Integer>>, Integer> accumulator() {
        return (Map<Boolean, List<Integer>> acc, Integer candidate) -> {
            final List<Integer> currentPrimes = acc.get(true);
            final Boolean isP = isPrime(currentPrimes, candidate);
            acc.get(isP).add(candidate);
        };
    }

    /**
     * 从质数列表里取出来，看看是不是candidate的约数。
     *
     * @param primes 质数列表
     * @param candidate 判断值
     * @return true -> 质数； false->非质数。
     */
    private Boolean isPrime(
            List<Integer> primes,
            Integer candidate) {
        return primes.stream().noneMatch(i -> candidate % i == 0);
    }

    @Override
    public BinaryOperator<Map<Boolean, List<Integer>>> combiner() {
        return (Map<Boolean, List<Integer>> map1, Map<Boolean, List<Integer>> map2) -> {
            map1.get(true).addAll(map2.get(true));
            map1.get(false).addAll(map2.get(false));
            return map1;
        };
    }

    @Override
    public Function<Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> finisher() {
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH));
    }
}