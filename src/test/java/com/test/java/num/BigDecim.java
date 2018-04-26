package com.test.java.num;

import java.math.BigDecimal;
import org.junit.Test;

public class BigDecim {

    @Test
    public void testDoubleConstructor() {
        BigDecimal bg1 = new BigDecimal(0.9);
        BigDecimal bg2 = new BigDecimal(1.0);
        System.out.println(bg2.subtract(bg1));//输出0.09999999999999997779553950749686919152736663818359375

        BigDecimal bg3= new BigDecimal("0.9");
        BigDecimal bg4 = new BigDecimal("1.0");
        System.out.println(bg4.subtract(bg3));//输出0.1
    }


}
