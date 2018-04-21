package com.test.java.stream.collect;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * 手动实现一个To List收集器. Created by Ryan Miao on 4/20/18.
 */
public class ToListCollector<T> implements Collector<T, List<T>, List<T>> {

    /**
     * 负责返回一个初始化的累加器实例.
     *
     * @return 容器提供者.
     */
    @Override
    public Supplier<List<T>> supplier() {
        return ArrayList::new;
    }

    /**
     * 执行归约操作。将next元素添加到累加器里，返回void，因为累加器原地更新.
     *
     * @return 规约操作.
     */
    @Override
    public BiConsumer<List<T>, T> accumulator() {
        return List::add;
    }

    /**
     * 执行规约操作的函数。定义了对流的各个子部分进行并行处理时，各个子部分规约所得的累加器要如何合并.
     *
     * @return 分组合并
     */
    @Override
    public BinaryOperator<List<T>> combiner() {

        return (list1, list2) -> {
            list1.addAll(list2);
            return list1;
        };
    }

    /**
     * 最后调用的函数，将累加器对象转换为对整个集合操作的最终结果。本处返回list本身.
     *
     * @return 最终转换函数。
     */
    @Override
    public Function<List<T>, List<T>> finisher() {
        return (i) -> i;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections
            .unmodifiableSet(
                EnumSet.of(Characteristics.IDENTITY_FINISH, Characteristics.CONCURRENT));
    }
}
