package com.ljm.lock.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

public class RecursionDemo {

    private static ReentrantLock lock = new ReentrantLock();

    private static void accessResource() {
        lock.lock();
        try {
            System.out.println("对资源处理" + lock.getHoldCount());
            if(lock.getHoldCount()<5){
                accessResource();
            }
        }finally {
            lock.unlock();
        }
        System.out.println(lock.getHoldCount());
    }

    public static void main(String[] args) {
        accessResource();
    }
}
