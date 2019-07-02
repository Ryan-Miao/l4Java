package com.test.java.thread.pool;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.junit.Ignore;
import org.junit.Test;

/**
 * @author Ryan Miao at 2018-07-25 17:59
 **/
@Ignore
public class ThreadPoolExecutorTest {

    /**
     * 参数初始化
     */
    private final int cpuCount = Runtime.getRuntime().availableProcessors();
    /**
     * 核心线程数量大小
     */
    private final int corePoolSize = Math.max(2, Math.min(cpuCount - 1, 4));
    /**
     * 线程池最大容纳线程数
     */
    private final int maximumPoolSize = cpuCount * 4 + 1;
    /**
     * 线程空闲后的存活时长
     */
    private static final int KEEP_ALIVE_TIME = 100;
    private final ThreadFactory threadFactory = new ThreadFactoryBuilder()
        .setNameFormat("install-poi-2-es-%d")
        .setDaemon(true)
        .build();
    private final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(corePoolSize,
        maximumPoolSize,
        KEEP_ALIVE_TIME, TimeUnit.MICROSECONDS,
        new ArrayBlockingQueue<>(100), threadFactory);

    @Test
    public void testShutDown() throws ExecutionException, InterruptedException {
        threadPoolExecutor.allowCoreThreadTimeOut(true);
        Future<Object> t1 = threadPoolExecutor.submit(() -> {
            try {
                Thread.sleep(1000);
                return "test";
            } catch (InterruptedException e) {
                e.printStackTrace();
                return "error";
            }
        });
        System.out.println(threadPoolExecutor.getActiveCount());
        System.out.println(threadPoolExecutor.getCompletedTaskCount());
        System.out.println(threadPoolExecutor.getCorePoolSize());
        System.out.println(threadPoolExecutor.getPoolSize());

        Object o = t1.get();
        System.out.println(o);

        Thread.sleep(10);

        System.out.println("-----------------");

        System.out.println(threadPoolExecutor.getActiveCount());
        System.out.println(threadPoolExecutor.getCompletedTaskCount());
        System.out.println(threadPoolExecutor.getCorePoolSize());
        System.out.println(threadPoolExecutor.getPoolSize());

        Future<Object> t2 = threadPoolExecutor.submit(() -> "test");
        Object o2 = t2.get();
        System.out.println(o2);
    }

    @Test
    public void testMaxNum() throws InterruptedException {
        for (int i = 0; i < 100000; i++) {
            int n = i;
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(n);
                }
            });
            thread.start();
        }

    }

}
