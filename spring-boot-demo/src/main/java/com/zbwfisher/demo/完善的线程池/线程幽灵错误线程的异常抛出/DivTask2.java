package com.zbwfisher.demo.完善的线程池.线程幽灵错误线程的异常抛出;

import java.util.concurrent.Callable;

/**
 * Created by zbw on 17/7/16.
 */
public class DivTask2 implements Callable {
    int a, b;
    public DivTask2(int a,int b){
        this.a=a;
        this.b=b;
    }
    @Override
    public Object call() throws Exception {
        double re = a/b;
        System.out.println(re);
        return  re;
    }
}
