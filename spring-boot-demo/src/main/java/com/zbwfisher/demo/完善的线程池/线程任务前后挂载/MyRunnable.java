package com.zbwfisher.demo.完善的线程池.线程任务前后挂载;

import java.util.concurrent.TimeUnit;

/**
 * Created by zbw on 17/7/16.
 */
public class MyRunnable implements Runnable {

    private String name;
    private String nameReture;


    public String getName() {
        return name;
    }

    public MyRunnable (String name){
        this.name = name;
    }

    public String getNameReture() {
        return nameReture;
    }

    @Override
    public void run() {

        try {
           Thread.sleep(2000);
            System.out.println("****正在执行 :Theard ID- "+Thread.currentThread().getId()+" Task name : "+name);
            nameReture = name + System.currentTimeMillis()+" 时间点";
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
