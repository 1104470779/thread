package com.ljm.lock.reentrantlock.fair;

import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class FairLock {

    public static void main(String[] args) {
        PrintQueue printQueue = new PrintQueue();
        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            threads[i] = new Thread(()->{
                System.out.println(Thread.currentThread().getName() + "开始打印");
                printQueue.print("------" + String.valueOf(finalI) + "-------");
                System.out.println(Thread.currentThread().getName() + "结束打印");
            });
        }

        for (int i = 0; i < 10; i++) {
            threads[i].start();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
class PrintQueue {
    private ReentrantLock lock = new ReentrantLock(false);

    public void print(String str) {
        lock.lock();
        try {
            long duration = new Random().nextInt(10) + 1;
            System.out.println(Thread.currentThread().getName() + "正在打印" + str + "，需要" + duration);
            Thread.sleep(duration * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

        lock.lock();
        try {
            long duration = new Random().nextInt(10) + 1;
            System.out.println(Thread.currentThread().getName() + "正在打印" + str+ "，需要" + duration);
            Thread.sleep(duration * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}