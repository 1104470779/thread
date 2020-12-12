package com.ljm.atomic;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * 原子数组的使用
 */
public class AtomicArray {

    static AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(1000);

    public static void main(String[] args) {
        Thread[] threadsIncrement = new Thread[100];
        Thread[] threadsDecrement = new Thread[100];
        for (int i = 0; i < threadsIncrement.length; i++) {
            threadsIncrement[i]  = new Thread(() -> {
                for (int j = 0; j < atomicIntegerArray.length() ; j++) {
                    atomicIntegerArray.getAndAdd(j, 2);
                }
            });
            threadsIncrement[i].start();
        }

        for (int i = 0; i < threadsDecrement.length; i++) {
            threadsDecrement[i]  = new Thread(() -> {
                for (int j = 0; j < atomicIntegerArray.length() ; j++) {
                    atomicIntegerArray.getAndAdd(j, -2);
                }
            });
            threadsDecrement[i].start();
        }

        for (int i = 0; i < threadsDecrement.length; i++) {
            try {
                threadsIncrement[i].join();
                threadsDecrement[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (int i = 0; i < atomicIntegerArray.length(); i++) {
            System.out.println(atomicIntegerArray);
        }


    }


}
