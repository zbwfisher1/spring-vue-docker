package com.zbwfisher.demo.thread.base;


import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by zbw on 17/6/16.
 */
public class BaseThreadClass {

    public static class ThreadPoolTask implements Runnable {
        public void run() {
            try {
                System.out.println("开始执行任务：" + Thread.currentThread().getName());
                Thread.sleep(1000);
                System.out.println(" - ");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void threadTest() {
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(1, 6, 1, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>());
        //通过循环来开启20个任务操作
        for (int i = 1; i <= 40; i++) {

            threadPool.execute(new ThreadPoolTask());
//            threadPool.execute(new ThreadPoolTask());
        }
    }

    public static void main(String[] args) {
        threadTest();
    }



}
