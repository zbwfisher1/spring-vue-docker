package com.zbwfisher.demo.完善的线程池.线程幽灵错误线程的异常抛出;

import java.util.concurrent.*;

/**
 * Created by zbw on 17/7/16.
 */
public class SimpleTest {

    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0,Integer.MAX_VALUE,0l, TimeUnit.SECONDS, new SynchronousQueue<>());
        for (int i = 0; i < 5; i++) {

            threadPoolExecutor.submit(new DivTask(100,i));
            //TODO 第一个不能捕获
            System.out.println(" --    - - - - - - -- - - - - - -  ");

//            Thread.sleep(3000);
            threadPoolExecutor.execute(new DivTask(100,i));

            //TODO 对于future的调用会 捕获异常
//            Future re =  threadPoolExecutor.submit(new DivTask2(100,i));
//            try {
//                re.get();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } catch (ExecutionException e) {
//                e.printStackTrace();
//            }
        }

    }
}
