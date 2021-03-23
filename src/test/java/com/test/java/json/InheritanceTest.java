package com.test.java.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.junit.Test;

/**
 * @author Ryan Miao at 2018-07-25 20:16
 **/
public class InheritanceTest {

    interface Man {

        int getAge();
    }

    @Data
    class Parent {

        private int id;
    }

    @EqualsAndHashCode(callSuper = true)
    @Data
    class Child extends Parent implements Man {

        private String name;

        @Override
        public int getAge() {
            return 100;
        }
    }

    @Test
    public void testSerialParentWithInstanceChild() throws JsonProcessingException {
        Child child = new Child();
        child.setName("child");
        child.setId(1);

        Parent parent = child;
        ObjectMapper objectMapper = new ObjectMapper();

        String s = objectMapper.writeValueAsString(parent);
        System.out.println(s);

        Man man = child;
        System.out.println(objectMapper.writeValueAsString(man));

        System.out.println(parent.getClass());
        System.out.println(man.getClass());
    }

}
