package com.zbwfisher.demo;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by zbw on 17/6/29.
 */
public class Counter {

//    public static int count = 0;

    public volatile static int count = 0;


    //是因为++不是原子操作，而不是volatile的问题。如果把count换为AtomicInteger类型就好了

//    public static AtomicInteger count = new AtomicInteger(0);


    public static void inc() {
        //这里延迟1毫秒，使得结果明显
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
        }

        count++;
//        count.incrementAndGet();

    }



    public static void main(String[] args) {
        //同时启动1000个线程，去进行i++计算，看看实际结果
        for (int i = 0; i < 10000; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Counter.inc();
                }
            }).start();
        }
        //这里每次运行的值都有可能不同,可能为1000
        System.out.println("运行结果:Counter.count=" + Counter.count);
    }



}
