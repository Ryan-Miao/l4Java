package com.test.java.math;

import com.test.java.num.BigDecim;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import org.junit.Assert;
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
    public void testRound() {
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

    @Test
    public void testCeil() {
        Integer a = 2;
        Integer b = 3;

        Integer r1 = b/a;
        Double r2 = b/(double)a;
        double ceil = Math.ceil(r2);
        Assert.assertEquals((Integer) 1, r1);
        Assert.assertEquals((Double) 1.5, r2);
        Assert.assertEquals(2, (int)ceil);
    }

}
