package com.zbwfisher.demo.thread.interrupted;

import java.util.concurrent.TimeUnit;

/**
 * Created by zbw on 17/7/8.
 */
public class test {

    public static void main(String[] args) {
        Thread t1 = new Thread() {

            @Override
            public void run() {
                while (true) {
                    if (Thread.currentThread().isInterrupted()) {
                        System.out.println(" 线程被中断");
                        break;
                    }
                    try {

                        TimeUnit.SECONDS.sleep(2);//TODO 这里是操作方法
                        System.out.println("输出徐呀的东西");
                    } catch (Exception e) {

                        System.out.println(" 异常线程中断");
                        Thread.currentThread().interrupt();
                        this.interrupt();
                    }

//                    Thread.currentThread().interrupt();


                    Thread.yield();
                }


            }

        };


        try {


            t1.setDaemon(true);
            t1.start();
            Thread.sleep(5000);
            t1.interrupt(); //TODO 实例只是打个中断标记

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
