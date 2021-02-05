package com.example.work.demo;

import com.example.work.utils.BaseMain;
import com.example.work.utils.Fiboracci;

import java.util.concurrent.Callable;

public class Main9 extends BaseMain {
    private final static int testValue = 28;

    public static void main(String[] args) {
        Callable<Integer> callable = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return Fiboracci.fiboracci(testValue);
            }
        };
        try {
            value = callable.call().intValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
        printInfo();
    }
}
