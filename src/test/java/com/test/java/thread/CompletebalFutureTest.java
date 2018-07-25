package com.test.java.thread;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import org.junit.Test;

/**
 * @author Ryan Miao at 2018-07-23 10:02
 **/
public class CompletebalFutureTest {

    @Test
    public void callbackTest() throws ExecutionException, InterruptedException {
        // Create a CompletableFuture
        CompletableFuture<String> whatsYourNameFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return "Rajeev";
        });

// Attach a callback to the Future using thenApply()
        CompletableFuture<String> greetingFuture = whatsYourNameFuture.thenApply(name -> {
            return "Hello " + name;
        });

// Block and get the result of the future.
        System.out.println(greetingFuture.get()); // Hello Rajeev
    }

}
