package com.test.java.math;

import com.test.java.num.BigDecim;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import org.junit.Test;

public class TestMath {

    @Test
    public void testMod() {
        double random = Math.random();
        System.out.println(random);

        int v = (int)(random * 1000) ;
        System.out.println(v);
    }

    @Test
    public void testRount() {
        int a = 25478033;
        int b = 458072581;

        double r = ((double) Math
            .round( a* 100 * 100.0 / b))
            / 100.0;
        System.out.println(r);

        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        String cResult = decimalFormat.format(a*100.0/b);
        System.out.println(cResult);//3.50


        int successRequestCount =25478033;
        int requestCount = 458072582;
        double v = Double
            .parseDouble(decimalFormat.format(successRequestCount * 100.0 / requestCount));
        System.out.println(v);
    }

}
