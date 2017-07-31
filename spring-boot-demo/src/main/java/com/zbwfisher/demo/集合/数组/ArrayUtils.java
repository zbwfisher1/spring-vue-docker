package com.zbwfisher.demo.集合.数组;

import java.util.Arrays;

/**
 * Created by zbw on 17/7/12.
 */
public class ArrayUtils {

    public static <T> T[] expandCapacity(T[] datas, int newLen) {
        newLen = newLen < 0 ? datas.length : datas.length + newLen;
        //生成一个新的数组
        return Arrays.copyOf(datas, newLen);
    }


    public static <T> T[] expandCapacity(T[] datas) {
        int newLen = (datas.length * 3) / 2 + 1;      //扩容原始数组的1.5倍
        //生成一个新的数组
        return Arrays.copyOf(datas, newLen);
    }


    public static <T> T[] expandCapacityMul(T[] datas, int mulitiple) {
        mulitiple = mulitiple < 0 ? 1 : mulitiple;
        int newLen = datas.length * mulitiple;
        return Arrays.copyOf(datas, newLen);
    }

    public static void main(String[] args) {
        Object[] arrayINT = new Object[10];

        arrayINT = expandCapacity(arrayINT);
        System.out.println(arrayINT.length);
    }
}
