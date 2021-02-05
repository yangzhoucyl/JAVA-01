package com.example.work.demo;

import com.example.work.utils.BaseMain;
import com.example.work.utils.Fiboracci;

import java.util.concurrent.CountDownLatch;

public class Main5 extends BaseMain {

    private final static int testValue = 28;

    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    public static void main(String[] args) {
        Thread thread = new Thread(() -> sumCountDownLatch(testValue));
        thread.start();
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        printInfo();
    }

    public static void sumCountDownLatch(int input) {
        value = Fiboracci.fiboracci(input);
        countDownLatch.countDown();
    }

}
