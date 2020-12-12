package com.ljm.threadpool;

//for循环创建10个线程，缺点：如果任务非常多，开销太大（反复的创建销毁线程），过多的线程占用太多的内存
public class ForLoop {

    public static void main(String[] args) {
        for (int i = 0; i < 100000; i++) {
            new Thread(()->{
                System.out.println("执行了任务！");
            }).start();
        }
    }
}
