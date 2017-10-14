package com.philaporter.utility;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class CustomTimer {

    public static Object determineDuration(Object obj, Method method, Object arg)
            throws InvocationTargetException, IllegalAccessException {

        System.out.printf("Starting %s...%n", method.getName());
        long start = System.currentTimeMillis();
        Object retObj = method.invoke(obj, arg);
        long end = System.currentTimeMillis();
        System.out.format("Finished %s in %.3f seconds%n", method.getName(), (((double)(end - start)) / 1000));
        return retObj;
    }
}
