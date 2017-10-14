package com.philaporter.utility;

import java.util.ArrayList;
import java.util.List;

public class PopulatedList {

    public static List<Integer> makeList(int size){
        List<Integer> list = new ArrayList<>(size);
        for(int i = 1; i <= size; i++) {
            list.add(i);
        }
        return list;
    }
}
