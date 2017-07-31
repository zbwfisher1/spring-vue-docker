package com.zbwfisher.demo.完善的线程池.线程任务前后挂载;


import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.*;

/**
 * Created by zbw on 17/7/16.
 */
public class ExtPoolExecutor {


    private static long currentTime = 0l;
    private static final int MAX_POOL_SIZE = 5;
    private static final int QUEUE_CAPACITY = 30;
    private static final int KEEP_ALIVE_TIME_VALUE = 30;

    private static final long lazyCheck = 1000 * 1 * 5;// 延迟多少时间后开始 检查
    private static final long periodCheck = 1000 * 1 * 15;// 检查频率


    //TODO 这里还是直接调用 ThreadPoolExecutor 方便方法调用
//    public static final ExecutorService  executorService;
    public static final ThreadPoolExecutor executorService = new ThreadPoolExecutor(5, 5, 0L, TimeUnit.SECONDS, new LinkedBlockingQueue<>(), new MyThreadFactory()) {

        @Override
        protected void beforeExecute(Thread t, Runnable r) {
            currentTime =System.currentTimeMillis();
            System.out.println(" 准备执行:" + ((MyRunnable) r).getName());
        }

        @Override
        protected void afterExecute(Runnable r, Throwable t) {
            System.out.println(" 执行完成:" + ((MyRunnable) r).getNameReture()+"  花费时间: "+(System.currentTimeMillis() - currentTime));
        }

        @Override
        protected void terminated() {
            System.out.println(" 线程池退出");
        }

    };

    /**
     * 自定义的线程工厂类, 用于为线程池创建线程对象.
     */
    private static class MyThreadFactory implements ThreadFactory {
        static int threadNumber = 0;

        @Override
        public Thread newThread(Runnable r) {
            String threadName = "thread-" + (threadNumber++);
            if (threadNumber == 1) {
                cheackPool();
            }
            System.out.println("@ 创建数据抽取任务线程 " + threadName);


            Thread thread = new Thread(r, threadName);
//            thread.setDaemon(true); //TODO 如果设置守护进程只能执行有效期为 主线程有效期

            return thread;
        }
    }

    public static void cheackPool() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {

                System.out.println("@抽取数据任务线程任务数:" + executorService.getTaskCount());
                System.out.println("@抽取数据任务线程活跃任务数:" + executorService.getActiveCount());
                System.out.println("@抽取数据任务线程任务队列数:" + executorService.getQueue().size());
                System.out.println("@抽取数据任务线程完成任务数:" + executorService.getCompletedTaskCount());
                System.out.println();
            }
        }, lazyCheck, periodCheck);
    }


    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 5; i++) {

            //TODO 这里 callbale 不能用
//            MyCallable myRunnable = new MyCallable(" 测试任务 - "+i);

            MyRunnable myRunnable = new MyRunnable(" 测试任务 - "+i);
            executorService.submit(myRunnable);
        }

        Thread.sleep(3000);

    }


}
