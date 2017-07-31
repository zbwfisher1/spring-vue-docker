package com.zbwfisher.demo.集合.数组;

/**
 * Created by zbw on 17/7/12.
 */
public class ArrayDemo {

    public static void main(String[] args) {
        int[] array = new int[10];
        System.out.println("array的父类是：" + array.getClass().getSuperclass());
        System.out.println("array的类名是：" + array.getClass().getName());

        int[] array_00 = new int[10];
        System.out.println("一维数组：" + array_00.getClass().getName());
        int[][] array_01 = new int[10][10];
        System.out.println("二维数组：" + array_01.getClass().getName());

        int[][][] array_02 = new int[10][10][10];
        System.out.println("三维数组：" + array_02.getClass().getName());

//        int[] array = new int[10];
        Class clazz = array.getClass();
//        [I没有生命任何成员变量、成员方法、构造函数、Annotation甚至连length成员变量这个都没有，
//          它就是一个彻彻底底的空类
        System.out.println(clazz.getDeclaredFields().length);
        System.out.println(clazz.getDeclaredMethods().length);
        System.out.println(clazz.getDeclaredConstructors().length);
        System.out.println(clazz.getDeclaredAnnotations().length);
        System.out.println(clazz.getDeclaredClasses().length);
    }


}
