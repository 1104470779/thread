package com.ljm.threadlocal;

public class ThreadLocalNPE {
    ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    public void set (){
        threadLocal.set(Thread.currentThread().getId());
    }

    //Long拆箱转long的时候抛出java.lang.NullPointerException
    public long get() {
        return threadLocal.get();
    }

    public static void main(String[] args) {
        ThreadLocalNPE threadLocalNPE = new ThreadLocalNPE();
        System.out.println(threadLocalNPE.get());
        threadLocalNPE.set();
        System.out.println(threadLocalNPE.get());
    }

}
