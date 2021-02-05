package com.example.work.demo;

import com.example.work.utils.BaseMain;

public class Main8 extends BaseMain {

    private final static int testValue = 28;

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            synchronized (Object.class){
                sum(testValue);
                Object.class.notify();
            }
        });
        thread.start();
        synchronized (Object.class){
            Object.class.wait();
            printInfo();
        }
    }
}
