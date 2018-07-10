package com.test.java.clazz.polimophic;

import com.test.java.clazz.polimophic.entity.Manager;
import org.junit.Test;

/**
 * @author Ryan Miao at 2018-07-09 14:20
 **/
public class InheritanceTest {

    @Test
    public void test() {
        Manager manager = new Manager("a", 1, 1);
        manager.setSalary(1);
        manager.setName("a");
        manager.setBonus(2);

        System.out.println(manager.getName());
        System.out.println(manager.getSalary());
    }

}
