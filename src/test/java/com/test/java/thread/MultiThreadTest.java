package com.test.java.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.junit.Test;

/**
 * @author ryan
 * @date 19-11-4 下午6:00
 */
public class MultiThreadTest {

    private int a = 0;

    private void increase() {
        a++;
    }

    public int getA() {
        return a;
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 10000, 10, TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(1));
        MultiThreadTest test = new MultiThreadTest();
        System.out.println("init");
        System.out.println(test.getA());

        CountDownLatch countDownLatch = new CountDownLatch(100);
        for (int i = 0; i < 8000; i++) {
            executor.submit(new Runnable() {
                @Override
                public void run() {
                        test.increase();
                        countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();
        System.out.println("result");
        System.out.println(test.getA());
        executor.shutdown();
    }

    @Test
    public void testRight() {
        int a = 8;
        System.out.println(a >> 1);
    }

}
