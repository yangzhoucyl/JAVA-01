package com.example.work.demo;

import com.example.work.utils.BaseMain;

import java.util.concurrent.locks.LockSupport;

public class Main10 extends BaseMain {

    private final static int testValue = 28;


    public static void main(String[] args) {
        Thread thread = Thread.currentThread();
        new Thread(() -> {
            sum(testValue);
            LockSupport.unpark(thread);
        }).start();
        LockSupport.park();
        printInfo();
    }
}
