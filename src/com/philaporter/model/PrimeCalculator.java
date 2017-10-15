package com.philaporter.model;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class PrimeCalculator {

    public static boolean isPrime(int num) {

        // Check for invalid
        if( num < 2 ) return false;
        // Check for 1st prime / only even prime
        if( num == 2 ) return true;
        // Interrogate num
        for(int i = 2; i <= num / 2; i++) {
            if(num % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static HashMap<Integer, Boolean> evaluateAll(List<Integer> list) {
        HashMap<Integer, Boolean> map = new HashMap<>();
        list.stream().forEach((num)->
                map.put(num, PrimeCalculator.isPrime(num))
        );
        return map;
    }

    public static HashMap<Integer, Boolean> evaluateAllConcurrentStream(List<Integer> list) {
        HashMap<Integer, Boolean> map = new HashMap<>();
        list.parallelStream().forEach((num)->
                map.put(num, PrimeCalculator.isPrime(num))
        );
        return map;
    }

    public static ConcurrentHashMap<Integer, Boolean> evaluateAllRecursiveTask(List<Integer> list) {
        ConcurrentHashMap<Integer, Boolean> map = new ConcurrentHashMap<>();
        list.stream().forEach((num)->
                map.put(num, PrimeCalculator.isPrime(num))
        );
        return map;
    }
}
