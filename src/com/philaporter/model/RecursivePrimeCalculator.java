package com.philaporter.model;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.RecursiveTask;


public class RecursivePrimeCalculator extends RecursiveTask {

    private int threshold = 10_000;
    private List<Integer> list;
    private ConcurrentHashMap<Integer, Boolean> map = new ConcurrentHashMap<>();

    public RecursivePrimeCalculator(List<Integer> list) {
        this.list = list;
    }

    @Override
    protected ConcurrentHashMap<Integer, Boolean> compute() {

        int size = list.size();
        if(size > threshold){

            // Divide up the work
            RecursivePrimeCalculator firstHalf = new RecursivePrimeCalculator(list.subList(0, size / 2));
            firstHalf.fork();
            RecursivePrimeCalculator secondHalf = new RecursivePrimeCalculator(list.subList(size / 2, size));
            map.putAll(secondHalf.compute());
            map.putAll((Map<? extends Integer, ? extends Boolean>) firstHalf.join());
            return map;
        }
        else
        {
            return PrimeCalculator.evaluateAllRecursiveTask(list);
        }
    }
}
