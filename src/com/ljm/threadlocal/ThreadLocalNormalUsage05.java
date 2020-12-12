package com.ljm.threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//加锁解决线程安全问题
public class ThreadLocalNormalUsage05 {

    public static ExecutorService threadPool = Executors.newFixedThreadPool(10);
//    public static ThreadLocal<SimpleDateFormat> simpleDateFormatThreadLocal = new ThreadLocal<SimpleDateFormat>() {
//        @Override
//        protected SimpleDateFormat initialValue() {
//            return new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
//        }
//    };
    public static ThreadLocal<SimpleDateFormat> simpleDateFormatThreadLocal =
        ThreadLocal.withInitial(()-> new SimpleDateFormat("yyyy-mm-dd hh:mm:ss"));


    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            threadPool.execute(() -> {
                String date = new ThreadLocalNormalUsage05().date(finalI);
                System.out.println(date);
            });
        }
        threadPool.shutdown();
    }

    public String date(int seconds) {
        //参数的单位是毫秒，从1970-1-1 00：00：00 秒计时
        String format = null;
        Date date = new Date(1000 * seconds);
        SimpleDateFormat simpleDateFormat = simpleDateFormatThreadLocal.get();
        return simpleDateFormat.format(date);
    }

}

