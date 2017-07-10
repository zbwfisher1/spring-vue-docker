package com.zbwfisher.demo.thread.daemon;

import java.util.concurrent.TimeUnit;

/**
 * Created by zbw on 17/7/9.
 */
public class DaemonT {

    public static  class DaemonDemo extends Thread{

        @Override
        public void run(){
            while (true){

                System.out.println("输出线程");
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        Thread t= new DaemonDemo();
        t.setDaemon(true);
        t.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
