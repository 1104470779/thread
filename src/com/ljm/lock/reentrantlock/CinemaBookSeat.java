package com.ljm.lock.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

public class CinemaBookSeat {

    private static ReentrantLock lock = new ReentrantLock();

    private static void bookSeat() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"开始预订");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"完成预定");
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 4; i++) {
            new Thread(()->{
                bookSeat();
            },"thread"+i).start();
        }
    }

}
