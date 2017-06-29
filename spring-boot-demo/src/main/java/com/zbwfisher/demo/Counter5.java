package com.zbwfisher.demo;

/**
 * Created by zbw on 17/6/29.
 */
public class Counter5 {
//
    static int i = 0, j = 0;
//  static volatile int i = 0, j = 0;
    static synchronized void one() {
        j++;
    }
    static synchronized void one1() {
        i++;
    }
    static void two() {
        if (i != j) {
            System.out.println("i=" + i + " j=" + j);
        }
    }
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 2000; i++) {
            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    one();
//                    one1();
                }
            });
            Thread t3 = new Thread(new Runnable() {
                @Override
                public void run() {
                    one1();
                }
            });


            Thread t2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    two();
                }
            });

            t1.start();
            t3.start();
            t2.start();
        }

        Thread.sleep(1000);

    }
}
