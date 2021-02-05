package com.example.work.demo;

import com.example.work.utils.BaseMain;

public class Main2 extends BaseMain {

    private final static int testValue = 28;

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> { sum(28); });
        thread.start();
        thread.join();
        printInfo();
    }


}
