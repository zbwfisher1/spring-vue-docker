package com.zbwfisher.demo.qitacheshi;

import java.util.Scanner;

/**
 * Created by zbw on 17/7/13.
 */
public class Test {

    public static void main(String[] args) {
        System.out.print("输入n：");
        int n = new Scanner(System.in).nextInt();
        double sum = 0;
        int i = 1;
        while (i <= n) {
            sum += (double) 1 / i++;
        }
        System.out.println(sum);
    }
}
