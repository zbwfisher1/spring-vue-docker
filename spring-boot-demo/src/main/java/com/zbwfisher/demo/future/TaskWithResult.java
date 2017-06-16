package com.zbwfisher.demo.future;

import java.util.Random;
import java.util.concurrent.Callable;

/**
 * Created by zbw on 17/6/16.
 */
public class TaskWithResult implements Callable<String> {

    private int id;

    public TaskWithResult(int id) {
        this.id = id;
    }

    /**
     * 任务的具体过程，一旦任务传给ExecutorService的submit方法，则该方法自动在一个线程上执行。
     */
    public String call() throws Exception {

        System.out.println("call()方法被自动调用,干活！！！             " + Thread.currentThread().getName());

//        if (new Random().nextBoolean())
//            throw new TaskException("Meet error in task." + Thread.currentThread().getName());

        return "call()方法被自动调用，任务的结果是：" + id + "    " + Thread.currentThread().getName();
    }
}
