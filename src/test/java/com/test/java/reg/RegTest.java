package com.test.java.reg;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Ryan Miao at 2018-06-29 15:06
 **/
public class RegTest {

    @Test
    public void testReg(){
        String reg = "^ROLE_.*";
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher("ROLE_aaa");
        boolean matches = matcher.matches();
        System.out.println(matches);
    }

    @Test
    public void testMobile() {
        String mobile = "18680331512";
        String reg =  "^1[3|4|5|7|8]\\d{9}$";
        Pattern pattern = Pattern.compile(reg);
        boolean matches = pattern.matcher(mobile).matches();
        Assert.assertTrue(matches);
    }

}
