package com.test.java.encode;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Base64;
import org.junit.Test;

/**
 * @author Ryan Miao at 2018-06-28 17:09
 **/
public class Base64Test {

    @Test
    public void testBase() throws UnsupportedEncodingException {
        String  str = "ryan";
        String encodedStr = Base64.getEncoder().encodeToString(str.getBytes("UTF-8"));
        System.out.println(encodedStr);

        byte[] decode = Base64.getDecoder().decode(encodedStr);

        String source = new String(decode, Charset.forName("UTF-8"));
        System.out.println(source);
    }

    @Test
    public void testRc4(){
        String rs = "FpjPXwN/bljcX2dlr8EfrtuAtyqO1IZm2k3SHBGYKFyPrPlSTikkrxGZngM7S7sL/papLmvf1EDhYC+6UmWNhFnL";
        byte[] getArr = Base64.getDecoder().decode(rs);

        String result = RC4T2.decry_RC4(getArr, "ded869f197ca7bec");
        System.out.println("RC4 decrypt:"+result);
    }

}
