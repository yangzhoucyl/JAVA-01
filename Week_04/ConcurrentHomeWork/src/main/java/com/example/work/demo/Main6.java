package com.example.work.demo;

import com.example.work.utils.BaseMain;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Main6 extends BaseMain {
    private final static int testValue = 28;

    private static CyclicBarrier barrier = new CyclicBarrier(1, () -> {
        sum(testValue);
    });

    public static void main(String[] args) {
        try {
            barrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        printInfo();
    }

}
