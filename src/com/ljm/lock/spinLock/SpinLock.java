package com.ljm.lock.spinLock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 自旋锁，比较头铁...
 */
public class SpinLock {

    private AtomicReference<Thread> sign =  new AtomicReference<>();

    public static void main(String[] args) {
        SpinLock spinLock = new SpinLock();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "开始尝试获取自旋锁");
                spinLock.lock();
                System.out.println(Thread.currentThread().getName() + "获取到了自旋锁");
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    spinLock.unLock();
                    System.out.println(Thread.currentThread().getName() + "释放锁");
                }
            }
        };
        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);
        thread1.start();
        thread2.start();
    }

    public void lock(){
        Thread currentThread = Thread.currentThread();
        while (!sign.compareAndSet(null, currentThread)) {
//            System.out.println(currentThread.getName() + "尝试获取锁。。。。");
        }

    }

    public void unLock() {
        Thread currentThread = Thread.currentThread();
        sign.compareAndSet(currentThread, null);
    }

}
