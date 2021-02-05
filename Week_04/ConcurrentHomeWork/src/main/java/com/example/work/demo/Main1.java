package com.example.work.demo;

import com.example.work.utils.BaseMain;
import com.example.work.utils.Fiboracci;

import java.util.concurrent.Semaphore;

public class Main1 extends BaseMain {

    private final static int testValue = 28;

    public static void main(String[] args) {
        Thread thread = new Thread(() -> sum(testValue));
        thread.start();
        while(true){
            if (value != 0){
                break;
            }
        }
        printInfo();
    }


}
