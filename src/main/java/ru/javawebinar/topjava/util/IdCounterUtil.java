package ru.javawebinar.topjava.util;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class IdCounterUtil {
    //private static List<Integer> allId;
    /*static{
        allId = new ArrayList<>();
        for(int i = 1; i < Integer.MAX_VALUE; i++){
            allId.add(i);
        }
        Collections.sort(allId);
    }*/

    public static int getNextId(Set<Integer> set){
        if(set.size() == 0){
            return 1;
        } else {
            List<Integer> list = new ArrayList<>(set);
            Collections.sort(list);
            AtomicInteger ai = new AtomicInteger(list.get(list.size() - 1));
            return ai.incrementAndGet();
        }
        /*allId.removeAll(set);
        return allId.get(0);*/
    }
}
