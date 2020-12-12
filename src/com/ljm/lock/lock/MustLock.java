package com.ljm.lock.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//finally释放锁，保证锁的释放
public class MustLock {

    private static Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        lock.lock();
        try {
            //获取本锁保护的资源
            System.out.println(Thread.currentThread().getName() + "开始执行");
        } finally {
            lock.unlock();
        }
    }
}
