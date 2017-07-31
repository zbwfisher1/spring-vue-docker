package com.zbwfisher.demo.集合.集合测试;

import java.util.*;


/**
 * Created by zbw on 17/7/12.
 */
public class CollectionsTest {

    private List<String> list = new ArrayList<String>();


    public static void main(String[] args) {
        CollectionsTest test = new CollectionsTest();
        test.init();
//        test.testUnmodifiable();

//        test.testSort();
//
//        test.testSearch();
//
        test.testOther();

    }

    public void init() {
        // 准备测试数据
        list.add("b张三");
        list.add("d孙六");
        list.add("a李四");
        list.add("e钱七");
        list.add("c赵五");
    }

    public void testUnmodifiable() {
        System.out.println("给定的list：" + list);
        List<String> unmodList = Collections.unmodifiableList(list);

        unmodList.add("再加个试试！"); // 抛出：java.lang.UnsupportedOperationException

        // 这一行不会执行了
        System.out.println("新的unmodList：" + unmodList);
    }


    public void testSort() {
//        reverse(List list)：反转指定List集合中元素的顺序
//        shuffle(List list)：对List中的元素进行随机排序（洗牌）System.out.println("原始顺序：" + list);
//        sort(List list)：对List里的元素根据自然升序排序
//        sort(List list, Comparator c)：自定义比较器进行排序Collections.reverse(list);
//        swap(List list, int i, int j)：将指定List集合中i处元素和j出元素进行交换System.out.println("reverse后顺序：" + list);
//        rotate(List list, int distance)：将所有元素向右移位指定长度，如果distance等于size那么结果不变
        Collections.shuffle(list);
        System.out.println("shuffle后顺序：" + list);

        Collections.swap(list, 1, 3);
        System.out.println("swap后顺序：" + list);

        Collections.sort(list);
        System.out.println("sort后顺序：" + list);

        Collections.rotate(list, 1);
        System.out.println("rotate后顺序：" + list);
    }

    public void testSearch() {
        System.out.println("给定的list：" + list);
        System.out.println("max：" + Collections.max(list));
        System.out.println("min：" + Collections.min(list));
        System.out.println("frequency：" + Collections.frequency(list, "a李四"));
        Collections.replaceAll(list, "a李四", "aa李四");
        System.out.println("replaceAll之后：" + list);

        // 如果binarySearch的对象没有排序的话，搜索结果是不确定的
        System.out.println("binarySearch在sort之前：" + Collections.binarySearch(list, "c赵五"));
        Collections.sort(list);
        // sort之后，结果出来了
        System.out.println("binarySearch在sort之后：" + Collections.binarySearch(list, "c赵五"));

        Collections.fill(list, "A");
        System.out.println("fill：" + list);
    }

    public void testOther() {
        List<String> list1 = new ArrayList<String>();
        List<String> list2 = new ArrayList<String>();

        // addAll增加变长参数
        Collections.addAll(list1, "大家好", "你好", "我也好");
        Collections.addAll(list2, "大家好", "a李四", "我也好");

        // disjoint检查两个Collection是否的交集
        boolean b1 = Collections.disjoint(list, list1);
        boolean b2 = Collections.disjoint(list, list2);
        System.out.println(b1 + "\t" + b2);

        // 利用reverseOrder倒序
        Collections.sort(list1, Collections.reverseOrder());
        System.out.println(list1);
    }
}
