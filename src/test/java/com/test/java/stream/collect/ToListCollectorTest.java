package com.test.java.stream.collect;

import com.google.common.collect.Lists;
import com.test.java.stream.entity.Dish;
import com.test.java.stream.entity.Dish.Type;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

/**
 * @author Ryan Miao
 * @date 18-4-22
 */
public class ToListCollectorTest {

    private final ArrayList<Dish> dishes = Lists.newArrayList(
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
    public void testCustomizedCollector() {
        List<Dish> rs = dishes.stream().collect(new ToListCollector<>());
        System.out.println(rs);
    }

}