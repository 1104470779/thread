package com.ljm.cas;

/**
 * 模拟cas
 */
public class SimulatedCAS {

    private volatile int value;

    //模拟cpu的原子操作
    public synchronized int compareAndSwap(int expect, int newValue) {
        int oldValue = value;
        if(oldValue == expect) {
            value = newValue;
        }
        return oldValue;
    }

    public static void main(String[] args) {

    }
}
