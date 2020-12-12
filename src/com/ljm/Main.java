package com.ljm;

public class Main {

    public static void main(String[] args) {
        String a = "12",b = "1234";
        int aLength = a.length(), bLength = b.length();
        if(a.length() < b.length()) {
            for (int i = 0; i < bLength - aLength; i++) {
                a = "0" + a;
            }
        } else {
            for (int i = 0; i < aLength - bLength; i++) {
                b = "0" +b;
            }
        }
        int add = 0;
        String result = "";
        for (int i = a.length() -1; i >= 0; i--) {
            int ret = Integer.valueOf(a.substring(i,i + 1)).intValue() + Integer.valueOf(b.substring(i, i + 1));
            int current;
            if(ret > 10) {
                current = Integer.valueOf(String.valueOf(ret).substring(1)) + add;
                add = Integer.valueOf(String.valueOf(ret).substring(0, 1));
            } else {
                current = ret;
            }
            result = String.valueOf(current) + result;
        }
        if(add != 0) {
            result  = String.valueOf(add) + result;
        }
        System.out.println(result);
    }
}
