package com.ljm.lock.lock;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * tryLock避免死锁
 */
public class TryLockDeadLock {

    static Lock lock1 = new ReentrantLock();
    static Lock lock2 = new ReentrantLock();

    public static void main(String[] args) {
        new Thread(()->{
            for (int i = 0; i < 100; i++) {
                    try {
                        if(lock1.tryLock(1, TimeUnit.SECONDS)) {
                            try {
                                System.out.println(Thread.currentThread().getName()+"获取到锁1");
                                TimeUnit.SECONDS.sleep(1);
                                if(lock2.tryLock(1,TimeUnit.SECONDS)) {
                                    try {
                                        System.out.println(Thread.currentThread().getName()+"获取到锁2");
                                        System.out.println(Thread.currentThread().getName() +"成功获取两把锁");
                                        break;
                                    } finally {
                                        lock2.unlock();
                                    }
                                } else {
                                    System.out.println(Thread.currentThread().getName()+"获取锁2失败，重试");
                                }
                            } finally {
                                lock1.unlock();
                                Thread.sleep(new Random().nextInt(1000));
                            }
                        } else {
                            System.out.println(Thread.currentThread().getName() + "获取锁1失败，重试");
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        },"thread-1").start();

        new Thread(()->{
            for (int i = 0; i < 100; i++) {
                try {
                    if(lock2.tryLock(1, TimeUnit.SECONDS)) {
                        try {
                            System.out.println(Thread.currentThread().getName()+"获取到锁2");
                            TimeUnit.SECONDS.sleep(1);
                            if(lock1.tryLock(1,TimeUnit.SECONDS)) {
                                try {
                                    System.out.println(Thread.currentThread().getName()+"获取到锁1");
                                    System.out.println(Thread.currentThread().getName() +"成功获取两把锁");
                                    break;
                                } finally {
                                    lock1.unlock();
                                }
                            } else {
                                System.out.println(Thread.currentThread().getName()+"获取锁1失败，重试");
                            }
                        } finally {
                            lock2.unlock();
                            Thread.sleep(new Random().nextInt(1000));
                        }
                    } else {
                        System.out.println(Thread.currentThread().getName() + "获取锁2失败，重试");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"thread-2").start();
    }

}
