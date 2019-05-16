package com.test.java.clazz.innerclass;

import lombok.Data;

public class AnonymousInnerClassTest {

    @Data
    static class Animal {
        private String name;

        public void sayHi(){
            System.out.println("Hi " + name);
        }
    }

    public static void main(String[] args) {
        Animal animal = new Animal(){
            @Override
            public void sayHi() {
                System.out.println("Wang");
            }
        };
        animal.sayHi();


        Animal animal2 = new Animal(){
            @Override
            public void sayHi() {
                System.out.println("Miao");
            }
        };
        animal2.sayHi();
    }
}
