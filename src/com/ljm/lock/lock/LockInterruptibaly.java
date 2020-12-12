package com.ljm.lock.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockInterruptibaly implements Runnable {

    private static Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        LockInterruptibaly lockInterruptibaly = new LockInterruptibaly();
        Thread thread1 = new Thread(lockInterruptibaly, "t1");
        Thread thread2 = new Thread(lockInterruptibaly, "t2");
        thread1.start();
        thread2.start();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread1.interrupt();
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "尝试获取锁");
        try {
            lock.lockInterruptibly();
            try {
                System.out.println(Thread.currentThread().getName()+ "得到锁");
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + "睡眠期间断");
            } finally {
                lock.unlock();
                System.out.println(Thread.currentThread().getName() + "释放锁");
            }
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + "获取锁期间被中断");
        }
    }
}
