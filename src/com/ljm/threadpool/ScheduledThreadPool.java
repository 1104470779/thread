package com.ljm.threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

//适合做定时任务，和时间相关的工作
public class ScheduledThreadPool {

//    public static void main(String[] args) {
//        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
//        scheduledExecutorService.schedule(()-> {
//            System.out.println("5s之后执行");
//        }, 5, TimeUnit.SECONDS);
//    }

    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
        scheduledExecutorService.scheduleAtFixedRate(()-> {
            System.out.println("开始1s之后执行，之后每隔3秒执行一次");
        }, 1,3, TimeUnit.SECONDS);
    }
}
