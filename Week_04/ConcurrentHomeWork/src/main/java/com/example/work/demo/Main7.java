package com.example.work.demo;

import com.example.work.utils.BaseMain;

import java.util.concurrent.TimeUnit;

public class Main7 extends BaseMain {

    private final static int testValue = 28;

    public static void main(String[] args) {
        new Thread(() -> sum(testValue)).start();
        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        printInfo();
    }
}
