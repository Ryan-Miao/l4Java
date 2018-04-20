package com.test.java.stream.collect;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.maxBy;
import static java.util.stream.Collectors.partitioningBy;
import static java.util.stream.Collectors.reducing;
import static java.util.stream.Collectors.summingInt;
import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.Collectors.toSet;

import com.google.common.collect.Lists;
import com.test.java.entity.User;
import com.test.java.stream.entity.CaloricLevel;
import com.test.java.stream.entity.Dish;
import com.test.java.stream.entity.Dish.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.Test;

/**
 * 测试流收集数据. Created by Ryan Miao on 4/18/18.
 */
public class CollectTest {

    final ArrayList<Dish> dishes = Lists.newArrayList(
            new Dish("pork", false, 800, Type.MEAT),
            new Dish("beef", false, 700, Type.MEAT),
            new Dish("chicken", false, 400, Type.MEAT),
            new Dish("french fries", true, 530, Type.OTHER),
            new Dish("rice", true, 350, Type.OTHER),
            new Dish("season fruit", true, 120, Type.OTHER),
            new Dish("pizza", true, 550, Type.OTHER),
            new Dish("prawns", false, 300, Type.FISH),
            new Dish("salmon", false, 450, Type.FISH)
    );

    @Test
    public void test() {
        long howManyDishes = (long) dishes.size();
    }

    @Test
    public void tetMaxMin() {
        // 为啥返回Optional？ 如果stream为null怎么办
        Optional<Dish> mostCalorieDish = dishes.stream()
                .max(comparingInt(Dish::getCalories));
        Optional<Dish> minCalorieDish = dishes.stream().min(comparingInt(Dish::getCalories));
        Double avgCalories = dishes.stream().collect(Collectors.averagingInt(Dish::getCalories));

        IntSummaryStatistics summaryStatistics = dishes.stream()
                .collect(Collectors.summarizingInt(Dish::getCalories));
        double average = summaryStatistics.getAverage();
        long count = summaryStatistics.getCount();
        int max = summaryStatistics.getMax();
        int min = summaryStatistics.getMin();
        long sum = summaryStatistics.getSum();

    }

    @Test
    public void testJoin() {
        //直接连接
        String join1 = dishes.stream().map(Dish::getName).collect(Collectors.joining());
        System.out.println(join1);

        //逗号
        String join2 = dishes.stream().map(Dish::getName).collect(Collectors.joining(", "));
        System.out.println(join2);
    }

    @Test
    public void testToList() {
        List<String> names = dishes.stream().map(Dish::getName).collect(toList());
    }

    @Test
    public void testToSet() {
        Set<Type> types = dishes.stream().map(Dish::getType).collect(Collectors.toSet());
    }

    @Test(expected = IllegalStateException.class)
    public void testToMap() {
        Map<Type, Dish> byType = dishes.stream().collect(toMap(Dish::getType, d -> d));
    }

    @Test
    public void testReducing() {
        //之前内置的几个api都是reducing的特殊情况，都可以直接用reduce实现
        //0作为起点
        Integer totalCalories = dishes.stream()
                .collect(reducing(0, Dish::getCalories, (i, j) -> i + j));
        Integer totalCalories2 = dishes.stream().collect(reducing(0, Dish::getCalories, Integer::sum));
        Optional<Integer> totalCalories3 = dishes.stream().map(Dish::getCalories).reduce(Integer::sum);
        int sum = dishes.stream().mapToInt(Dish::getCalories).sum();

        //第一个项目作为起点
        Optional<Dish> mostCalorieDish = dishes.stream()
                .collect(reducing((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2));

        //手动实现toListCollector  --- 滥用reduce， 不可变的规约---不可以并行
        List<Integer> calories = dishes.stream().map(Dish::getCalories)
                .reduce(new ArrayList<Integer>(),
                        (List<Integer> l, Integer e) -> {
                            l.add(e);
                            return l;
                        },
                        (List<Integer> l1, List<Integer> l2) -> {
                            l1.addAll(l2);
                            return l1;
                        }
                );

        System.out.println(calories);


    }

    @Test
    public void testGroupBy() {
        Map<Type, List<Dish>> dishesByType = dishes.stream().collect(groupingBy(Dish::getType));

        Map<CaloricLevel, List<Dish>> dishesByLevel = dishes.stream()
                .collect(groupingBy(this::getCaloricLevel));
        System.out.println(dishesByType);
        System.out.println(dishesByLevel);

        //多级分组
        Map<Type, Map<CaloricLevel, List<Dish>>> byTypeAndCalory = dishes.stream().collect(
                groupingBy(Dish::getType, groupingBy(this::getCaloricLevel)));
        byTypeAndCalory.forEach((type, byCalory) -> {
            System.out.println("----------------------------------");
            System.out.println(type);
            byCalory.forEach((level, dishList) -> {
                System.out.println("\t" + level);
                System.out.println("\t\t" + dishList);
            });
        });

        Map<Type, Long> typesCount = dishes.stream().collect(groupingBy(Dish::getType, counting()));
        System.out.println(typesCount);

        Map<Type, Dish> mostCaloricByType = dishes.stream()
                .collect(toMap(Dish::getType, Function.identity(),
                        BinaryOperator.maxBy(comparingInt(Dish::getCalories))));
        System.out.println(mostCaloricByType);

        Map<Type, Integer> totalCaloriesByType = dishes.stream()
                .collect(groupingBy(Dish::getType, summingInt(Dish::getCalories)));

        Map<Type, Set<CaloricLevel>> caloricLevelsByType = dishes.stream()
                .collect(groupingBy(Dish::getType, mapping(this::getCaloricLevel, toSet())));

        Map<Type, Set<CaloricLevel>> caloricLevelsByType2 = dishes.stream()
                .collect(
                        groupingBy(Dish::getType, mapping(this::getCaloricLevel, toCollection(HashSet::new))));
    }

    @Test
    public void testPartition() {
        Map<Boolean, List<Dish>> partitionedMenu = dishes.stream()
                .collect(partitioningBy(Dish::isVegetarian));
        System.out.println(partitionedMenu.get(true));

        List<Dish> vegetarianDishes = dishes.stream().filter(Dish::isVegetarian)
                .collect(Collectors.toList());
        System.out.println(vegetarianDishes);

        Map<Boolean, Map<Type, List<Dish>>> vegetarianDishesByType = dishes.stream()
                .collect(partitioningBy(Dish::isVegetarian, groupingBy(Dish::getType)));
        Map<Boolean, Integer> vegetarianDishesTotalCalories = dishes.stream()
                .collect(partitioningBy(Dish::isVegetarian, summingInt(Dish::getCalories)));
        Map<Boolean, Dish> mostCaloricPartitionedByVegetarian = dishes.stream()
                .collect(partitioningBy(Dish::isVegetarian,
                        collectingAndThen(maxBy(comparingInt(Dish::getCalories)), Optional::get)));

        Map<Boolean, List<Integer>> partitionPrimes = IntStream.rangeClosed(2, 100).boxed()
                .collect(partitioningBy(this::isPrime));
        System.out.println(partitionPrimes.get(true));

    }

    @Test
    public void testCustomizedCollector() {
        List<Dish> rs = dishes.stream().collect(new ToListCollector<>());
        System.out.println(rs);
    }

    @Test
    public void testCustomizedCollectorForPrime() {
        Map<Boolean, List<Integer>> rs = IntStream.rangeClosed(2, 100).boxed()
                .collect(new PrimeNumbersCollector());
        System.out.println(rs.get(true));
    }

    @Test
    public void testCustormizedPrimeCollectorPerf() {
        Long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            Map<Boolean, List<Integer>> rs = IntStream.rangeClosed(2, 100).boxed()
                    .collect(new PrimeNumbersCollector());

        }
    }

    private boolean isPrime(int candidate) {
        int candidateRoot = (int) Math.sqrt((double) candidate);
        return IntStream.rangeClosed(2, candidateRoot).noneMatch(i -> candidate % i == 0);
    }

    private CaloricLevel getCaloricLevel(Dish d) {
        if (d.getCalories() <= 400) {
            return CaloricLevel.DIET;
        } else if (d.getCalories() <= 700) {
            return CaloricLevel.NORMAL;
        } else {
            return CaloricLevel.FAT;
        }
    }
}