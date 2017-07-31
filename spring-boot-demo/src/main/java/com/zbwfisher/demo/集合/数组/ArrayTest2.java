package com.zbwfisher.demo.集合.数组;

import java.util.Arrays;
import java.util.List;

/**
 * Created by zbw on 17/7/12.
 */
public class ArrayTest2 {
    public static void main(String[] args) {
        Person person_01 = new Person("chenssy_01");

        Person[] persons1 = new Person[]{person_01};
        Person[] persons2 = Arrays.copyOf(persons1,persons1.length);

        System.out.println("数组persons1:");
        display(persons1);
        System.out.println("---------------------");
        System.out.println("数组persons2:");
        display(persons2);


        //改变其值
        persons2[0].setName("chessy_02");
        System.out.println("------------改变其值后------------");
        System.out.println("数组persons1:");
        display(persons1);
        System.out.println("---------------------");
        System.out.println("数组persons2:");
        display(persons2);


        int[] datas = new int[]{1,2,3,4,5};
        List list = Arrays.asList(datas);
        System.out.println(list.size());


        Week[] weeks = {Week.Sum,Week.Mon,Week.Tue,Week.Web,Week.Thu,Week.Fri};
        List<Week> list2 = Arrays.asList(weeks);
        list2.add(Week.Sat);
    }
    public static void display(Person[] persons){
        for(Person person : persons){
            System.out.println(person.getName().toString());
        }
    }
}
