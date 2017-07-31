package com.zbwfisher.demo.完善的线程池.线程幽灵错误线程的异常抛出;

import java.util.concurrent.*;

/**
 * Created by zbw on 17/7/16.
 */
public class TraceThreadPoolExecutor extends ThreadPoolExecutor {
    public TraceThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    @Override
    public void execute(Runnable task) {
        super.execute(wrap(task, clientTrace(), Thread.currentThread().getName()));
    }

    @Override
    public Future<?> submit(Runnable task) {
        return super.submit(wrap(task, clientTrace(), Thread.currentThread().getName()));
    }

    private Exception clientTrace() {
        return new Exception(" 这里是测试 抛出异常");
    }

    private Runnable wrap(final Runnable task, final Exception clientStack, String clientThreadName) {
        return new Runnable() {
            @Override
            public void run() {
                try {
                    task.run();
                } catch (Exception e) {
                    clientStack.printStackTrace();
                    Exception exception = new RuntimeException(clientThreadName);
                    exception.printStackTrace();
                    e.printStackTrace();

                    throw e;
                }

            }
        };

    }


}
