package com.ljm.threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;

//10个线程分别打印日期
public class ThreadLocalNormalUsage01 {



    public static void main(String[] args) {
        for (int i = 0; i < 30; i++) {
            int finalI = i;
            new Thread(() -> {
                String date = new ThreadLocalNormalUsage01().date(finalI);
                System.out.println(date);
            }).start();
        }
    }

    public String date(int seconds) {
        //参数的单位是毫秒，从1970-1-1 00：00：00 秒计时
        Date date = new Date(1000 * seconds);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return simpleDateFormat.format(date);
    }

}
