package com.test.java.clazz.polimophic.entity;

/**
 * @author Ryan Miao at 2018-07-09 14:19
 **/
public class Manager extends Employee {

    private int bonus;

    public Manager(String name, int salary, int bonus) {
        super(name, salary);
        this.bonus = bonus;
    }

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    @Override
    public String getName() {
        return "manager: " + super.getName();
    }

    @Override
    public int getSalary() {
        return super.getSalary() + bonus;
    }

    @Override
    public String toString() {
        return "Manager{"
            + super.toString() +
            "bonus=" + bonus +
            '}';
    }
}
