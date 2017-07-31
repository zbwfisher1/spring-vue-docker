package com.zbwfisher.demo.集合.列表;

import java.util.*;

/**
 * Created by zbw on 17/7/12.
 */
public class ListTest {


    public static void main(String[] args) {
        List list = Collections.synchronizedList(new ArrayList());


        Map m = Collections.synchronizedMap(new HashMap());
        Set s = m.keySet();  // Needn't be in synchronized block
        synchronized (m) {  // Synchronizing on m, not s!
            Iterator i = s.iterator(); // Must be in synchronized block
            while (i.hasNext())
                System.out.println((i.next()));
        }
    }


}
