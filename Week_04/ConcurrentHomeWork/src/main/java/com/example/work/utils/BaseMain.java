package com.example.work.utils;

public class BaseMain {

    public volatile static int value = 0;

    public static long start = System.currentTimeMillis();

    public static void sum(int testValue){
        value = Fiboracci.fiboracci(testValue);
    }

    public static void printInfo(){
        System.out.println("计算结果为：" + value);
        System.out.println("花费时间：" +  (System.currentTimeMillis() - start) + " ms" );
    }
}
