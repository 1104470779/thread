package com.ljm.lock.lock;

import java.util.concurrent.atomic.AtomicInteger;

public class OptimismLock {

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger();//乐观锁
        atomicInteger.incrementAndGet();
    }

    int a;

    public synchronized void incremnet(){
        a++;
    }

}
