package com.ljm.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 原子类也可以实现线程安全
 */
public class AtomicIntegerDemo1 {

    private static final AtomicInteger atomicInteger = new AtomicInteger();

    public static void incrementAtomic () {
        atomicInteger.incrementAndGet();
    }

    private static volatile int basicCount = 0;
    public static void basicIncrement () {
        synchronized (AtomicIntegerDemo1.class){
            basicCount ++;
        }
    }
    public static void main(String[] args) {
        Thread t1 = new Thread(()->{
            for (int i = 0; i < 10000; i++) {
                incrementAtomic();
                basicIncrement();
            }
        });
        Thread t2 = new Thread(()->{
            for (int i = 0; i < 10000; i++) {
                incrementAtomic();
                basicIncrement();
            }
        });
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("atomic : " + atomicInteger.get());
        System.out.println("basic : " + basicCount);

    }

}
