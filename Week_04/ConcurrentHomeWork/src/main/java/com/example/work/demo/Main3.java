package com.example.work.demo;

import com.example.work.utils.BaseMain;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Main3 extends BaseMain {

    private final static int testValue = 28;


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask<Integer>(() -> {
            sum(testValue);
            return value;
        });
        new Thread(futureTask).start();
        futureTask.get();
        printInfo();
    }
}
