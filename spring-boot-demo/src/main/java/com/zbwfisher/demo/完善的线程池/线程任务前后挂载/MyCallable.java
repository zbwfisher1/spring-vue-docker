package com.zbwfisher.demo.完善的线程池.线程任务前后挂载;

import java.util.concurrent.Callable;

/**
 * Created by zbw on 17/7/16.
 */
public class MyCallable implements Callable{


    private String name;
    private String nameReture;


    //TODO 通过格式化的 get函数 处理开始任务状态 和 结束任务状态 也行
    public MyCallable(String name){
        this.name = name;
    }

    public String getNameReture() {
        return nameReture;
    }

    @Override
    public Object call() throws Exception {

        System.out.println(" 正在执行 :Theard ID"+Thread.currentThread().getId()+" Task name : "+name);

        nameReture += System.currentTimeMillis()+" 时间点";

        return null;
    }
}
