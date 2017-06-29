package com.zbwfisher.demo;

import java.util.concurrent.CountDownLatch;

/**
 * Created by zbw on 17/6/29.
 */
public class VolatileDemo {
    private static volatile int value;
    private static CountDownLatch countDownLatch = new CountDownLatch(10000);

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 10000; i++) {
            new Thread() {
                @Override
                public void run() {
                    increment();
                    countDownLatch.countDown();
                }
            }.start();

        }
        countDownLatch.await();
        System.out.println(getValue());
    }

    public synchronized static int increment() {
        return value++;
    }

    public static int getValue() {
        return value;
    }

}
