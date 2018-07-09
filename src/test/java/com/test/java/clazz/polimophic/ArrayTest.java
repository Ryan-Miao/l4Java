package com.test.java.clazz.polimophic;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

/**
 * @author Ryan Miao at 2018-07-09 09:29
 **/
public class ArrayTest {

    @Test(expected = ArrayStoreException.class)
    public void assign(){
        Man[]  man = new YellowMan[10];
        man[0] = new Man();
    }

}
