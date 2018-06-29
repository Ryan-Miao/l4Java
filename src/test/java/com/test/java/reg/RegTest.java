package com.test.java.reg;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
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

}
