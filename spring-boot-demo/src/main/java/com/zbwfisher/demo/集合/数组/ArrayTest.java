package com.zbwfisher.demo.集合.数组;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by zbw on 17/7/12.
 */
public class ArrayTest {

    public static void ensureCapacity(int minCapacity) {
        int modCount = 20;
        Object[] elementData = new Object[9];
        modCount++;
        int oldCapacity = elementData.length;
        /**
         * 若当前需要的长度超过数组长度时进行扩容处理
         */
        if (minCapacity > oldCapacity) {
            Object oldData[] = elementData;
            int newCapacity = (oldCapacity * 3) / 2 + 1;    //扩容
            if (newCapacity < minCapacity)
                newCapacity = minCapacity;
            //拷贝数组，生成新的数组
            elementData = Arrays.copyOf(elementData, newCapacity);
        }

        System.out.println(elementData.length);
    }

    public static void main(String[] args) {

//        ensureCapacity(11);
//
//    }
//
//        int[] arrays = new int[100000000];
////        List  list = new ArrayList<>();
//        Long sum = 0l;
//        Long time1 = System.currentTimeMillis();
//        for (int i = 0; i < 100000000; i++) {
//            sum += arrays[i % 10];
//        }
//        Long time2 = System.currentTimeMillis();
//        System.out.println("数组求和所花费时间：" + (time2 - time1) + "毫秒");
//
//
//
////        List<> list2 = new ArrayList<>(1000000);
//        Long time3 = System.currentTimeMillis();
//        for (int i = 0; i < 100000000; i++) {
//            sum += list.get(i % 10);
//        }
//        Long time4 = System.currentTimeMillis();
//        System.out.println("List求和所花费时间：" + (time4 - time3) + "毫秒");


        Long time1 = System.currentTimeMillis();
        List<Integer> list = new ArrayList<>(100000000);
        for (int i = 0; i < 100000000; i++) {
            list.add(1);
        }


        System.out.println(" shijian "+(System.currentTimeMillis()-time1));
        Long time2 = System.currentTimeMillis();
        List<Integer> list2 = new ArrayList<>();
        for (int i = 0; i < 100000000; i++) {
            list2.add(1);
        }

        System.out.println(" shijian "+(System.currentTimeMillis()-time2));

    }
}
