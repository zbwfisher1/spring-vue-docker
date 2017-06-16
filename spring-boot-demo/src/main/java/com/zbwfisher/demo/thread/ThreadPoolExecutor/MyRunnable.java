package com.zbwfisher.demo.thread.ThreadPoolExecutor;

/**
 * Created by zbw on 17/6/16.
 */
public class MyRunnable implements Runnable {
    private int num;

    public MyRunnable(int num) {
        this.num = num;
    }

    @Override
    public void run() {
        System.out.println("正在执行的MyRunnable " + num);
        try {
//            Thread.currentThread().sleep(4000);
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("MyRunnable " + num + "执行完毕");

    }
}
