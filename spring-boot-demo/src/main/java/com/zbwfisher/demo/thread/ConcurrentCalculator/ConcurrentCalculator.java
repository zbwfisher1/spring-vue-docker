package com.zbwfisher.demo.thread.ConcurrentCalculator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by zbw on 17/6/18.
 */
public class ConcurrentCalculator {

    private ExecutorService exec;
    private int cpuCoreNumber;
    private List<Future<Long>> tasks = new ArrayList<Future<Long>>();

    // 内部类
    class SumCalculator implements Callable<Long> {
        private int[] numbers;
        private int start;
        private int end;

        public SumCalculator(final int[] numbers, int start, int end) {
            this.numbers = numbers;
            this.start = start;
            this.end = end;
        }

        public Long call() throws Exception {
            Long sum = 0l;
            for (int i = start; i < end; i++) {

                TimeUnit.SECONDS.sleep(1);
                sum += numbers[i];
            }
            return sum;
        }
    }

    public ConcurrentCalculator() {
        cpuCoreNumber = Runtime.getRuntime().availableProcessors();
//        cpuCoreNumber = 1;
        exec = Executors.newFixedThreadPool(cpuCoreNumber);
    }

    public Long sum(final int[] numbers) {
        // 根据CPU核心个数拆分任务，创建FutureTask并提交到Executor
        for (int i = 0; i < cpuCoreNumber; i++) {
            int increment = numbers.length / cpuCoreNumber + 1;
            int start = increment * i;
            int end = increment * i + increment;
            if (end > numbers.length)
                end = numbers.length;
            SumCalculator subCalc = new SumCalculator(numbers, start, end);
            FutureTask<Long> task = new FutureTask<Long>(subCalc);
            tasks.add(task);
            if (!exec.isShutdown()) {
                exec.submit(task);
            }
        }
        return getResult();
    }

    /**
     * 迭代每个只任务，获得部分和，相加返回
     *
     * @return
     */
    public Long getResult() {
        Long result = 0l;
        for (Future<Long> task : tasks) {
            try {
                // 如果计算未完成则阻塞
                Long subSum = task.get();
                result += subSum;
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public void close() {
        exec.shutdown();
    }
}
