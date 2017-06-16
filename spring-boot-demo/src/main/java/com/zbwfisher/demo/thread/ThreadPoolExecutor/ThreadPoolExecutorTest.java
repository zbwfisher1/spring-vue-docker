package com.zbwfisher.demo.thread.ThreadPoolExecutor;

import java.util.concurrent.*;

/**
 * Created by zbw on 17/6/16.
 */
public class ThreadPoolExecutorTest {

    final static int corePoolSize = 1;
    final static int maximumPoolSize = 1;
    //当线程数大于核心线程时，此为终止前多余的空闲线程等待新任务的最长时间
    final static long keepAliveTime = 200;
    //执行前用于保持任务的队列5，即任务缓存队列
//    final static ArrayBlockingQueue<Runnable> workQueue =new ArrayBlockingQueue<Runnable>(5);

//    static ArrayBlockingQueue workQueue = new ArrayBlockingQueue(5);


    static LinkedBlockingQueue workQueue = new LinkedBlockingQueue(5);

    public static void main(String[] args) {
        //构建一个线程池，正常线程数量为5，最大线程数据为10，等待时间200


        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.MINUTES, workQueue);
        //线程池去执行15个任务
        for (int i = 0; i < 15; i++) {
            try{


                MyRunnable myRunnable = new MyRunnable(i);
                threadPoolExecutor.execute(myRunnable);


//                MyCallable myCallable = new MyCallable(i);
//                Future<String> future = threadPoolExecutor.submit(myCallable);
//
//                //TODO future 的调用会卡方法
//                System.out.println(future.get());



                System.out.println("线程池中现在的线程数目是："+threadPoolExecutor.getPoolSize()
                        +",  队列中正在等待执行的任务数量为："+ threadPoolExecutor.getQueue().size());
            }catch (Exception e){
//                e.printStackTrace();
            }

        }
        //关掉线程池
        threadPoolExecutor.shutdown();

    }


}
