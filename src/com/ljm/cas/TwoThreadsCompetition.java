package com.ljm.cas;

/**
 * 模拟CAS
 */
public class TwoThreadsCompetition implements  Runnable{

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
        TwoThreadsCompetition r = new TwoThreadsCompetition();
        r.value = 0;
        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(r.value);

    }

    @Override
    public void run() {
        compareAndSwap(0, 1);
    }
}
