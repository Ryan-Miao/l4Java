package com.test.spring;

import org.junit.Test;
import org.springframework.util.AntPathMatcher;

/**
 * @author ryan
 * @date 19-11-6 上午9:58
 */
public class TestMatcher {

    @Test
    public void testAntMatcher() {

        AntPathMatcher matcher = new AntPathMatcher();
        boolean match = matcher
            .match("/swagger**/**", "/swagger-resources/api-docs");
        if(match) {
            System.out.println(match);
        }

    }

}
