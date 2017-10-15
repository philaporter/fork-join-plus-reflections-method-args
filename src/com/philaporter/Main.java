package com.philaporter;

import com.philaporter.model.PrimeCalculator;
import com.philaporter.model.RecursivePrimeCalculator;
import com.philaporter.utility.CustomTimer;
import com.philaporter.utility.PopulatedList;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

public class Main {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        // Create a list for interrogation
        List<Integer> list =
                (List<Integer>) CustomTimer.determineDuration(
                new PopulatedList(),
                PopulatedList.class.getMethod("makeList", int.class), 1_000_000);

        // Single Thread
        Map<Integer, Boolean> map =
                (Map<Integer, Boolean>) CustomTimer.determineDuration(
                new PrimeCalculator(),
                PrimeCalculator.class.getMethod("evaluateAll", List.class), list);

        // Parallel stream
        Map<Integer, Boolean> map2 =
                (Map<Integer, Boolean>) CustomTimer.determineDuration(
                        new PrimeCalculator(),
                        PrimeCalculator.class.getMethod("evaluateAllConcurrentStream", List.class), list);

        // "Divide and Conquer" thread strategy
        RecursivePrimeCalculator rpc = new RecursivePrimeCalculator(list);
        ConcurrentHashMap<Integer, Boolean> concurrentHashMap =
                (ConcurrentHashMap<Integer, Boolean>) CustomTimer.determineDuration(
                        new ForkJoinPool(8),
                        ForkJoinPool.class.getMethod("invoke", ForkJoinTask.class), rpc);
    }
}
