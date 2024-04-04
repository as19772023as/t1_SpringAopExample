package ru.strebkov.t1_SpringAopExample.utils;

public class ThreadUtils {
    public ThreadUtils() {
    }

    public static void waitTime(long time)  {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
