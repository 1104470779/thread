package com.ljm.lock.reentrantlock.readwrite;

import java.util.concurrent.locks.ReentrantReadWriteLock;

//读写锁，多个线程可以同时读，但是不能同时写
public class CinemaReadWrite {

    private static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    private static ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
    private static ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();

    public static void main(String[] args) {
        new Thread(()->read()).start();
        new Thread(()->read()).start();
        new Thread(()->write()).start();
        new Thread(()->write()).start();
    }

    private static void read() {
        readLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "获取到读锁，正在读取");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } finally {
            readLock.unlock();
            System.out.println(Thread.currentThread().getName() + "释放读取");

        }
    }

    private static void write() {
        writeLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "获取到写锁，正在读取");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } finally {
            writeLock.unlock();
            System.out.println(Thread.currentThread().getName() + "释放写取");

        }
    }

}
