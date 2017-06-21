package com.zbwfisher.demo.thread.ConcurrentCalculator;

/**
 * Created by zbw on 17/6/18.
 */
public class Main {

    /**
     * 任务更具cpu的个数进行切割
     */
    public static void main(String[] args) {
        Long startTime = System.currentTimeMillis();
        int[] numbers = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 10, 11 };
        ConcurrentCalculator calc = new ConcurrentCalculator();
        Long sum = calc.sum(numbers);
        System.out.println("time: "+(System.currentTimeMillis()-startTime));
        System.out.println(sum);
        calc.close();
    }
}
