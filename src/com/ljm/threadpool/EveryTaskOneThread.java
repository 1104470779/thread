package com.ljm.threadpool;


//只有一个子线程
public class EveryTaskOneThread {

    public static void main(String[] args) {
       new Thread(()->{
            System.out.println("执行了任务！");
        }).start();
    }

}
