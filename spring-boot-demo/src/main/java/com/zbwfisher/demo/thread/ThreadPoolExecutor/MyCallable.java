package com.zbwfisher.demo.thread.ThreadPoolExecutor;

import java.util.concurrent.Callable;

/**
 * Created by zbw on 17/6/16.
 */
public class MyCallable implements Callable<String> {

    private int num;

    public MyCallable(int num) {
        this.num = num;
    }

    @Override
    public String call() throws Exception {

        System.out.println("正在执行的MyRunnable " + num);
        try {
//            Thread.currentThread().sleep(4000);
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ("MyCallable " + num + "执行完毕");
    }
}
