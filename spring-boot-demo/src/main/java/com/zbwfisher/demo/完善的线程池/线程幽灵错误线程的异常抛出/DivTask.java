package com.zbwfisher.demo.完善的线程池.线程幽灵错误线程的异常抛出;

/**
 * Created by zbw on 17/7/16.
 */
public class DivTask implements Runnable{

    int a, b;
    public DivTask(int a,int b){
        this.a=a;
        this.b=b;
    }

    @Override
    public void run() {
        double re = a/b;
        System.out.println(re);

    }
}
