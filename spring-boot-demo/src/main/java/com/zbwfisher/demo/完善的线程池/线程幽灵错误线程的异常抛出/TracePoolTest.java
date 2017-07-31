package com.zbwfisher.demo.完善的线程池.线程幽灵错误线程的异常抛出;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by zbw on 17/7/16.
 */
public class TracePoolTest {

    public static void main(String[] args) {
        ThreadPoolExecutor poolExecutor = new TraceThreadPoolExecutor(0,Integer.MAX_VALUE,0l, TimeUnit.SECONDS,new SynchronousQueue<>());

        for (int i = 0; i < 5; i++) {

            poolExecutor.submit(new DivTask(100,i));
            //TODO 第一个不能捕获



//            Thread.sleep(3000);
//            poolExecutor.execute(new DivTask(100,i));

        }

    }
}
