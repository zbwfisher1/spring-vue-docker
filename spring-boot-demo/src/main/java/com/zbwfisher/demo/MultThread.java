package com.zbwfisher.demo;

/**
 * Created by zbw on 17/6/29.
 */
public class MultThread{
    private static int count=0;
    private static int count2=0;
    public static void inc(){
        for(int i=0;i<=10000;i++){
            count++;
            //  System.out.println(count);//注释掉这句和加上这句区别
        }
    }
    public static void main(String[] args) throws InterruptedException{

        new Thread(
                new Runnable() {

                    public void run() {
                        inc();
                    }
                }
        ).start();
        for(int i=0;i<=10000;i++){
            count2++;
        }

        System.out.println(count+"   "+count2);
    }
}