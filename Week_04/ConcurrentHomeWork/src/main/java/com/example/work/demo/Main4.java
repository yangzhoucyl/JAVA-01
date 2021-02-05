package com.example.work.demo;

import com.example.work.utils.BaseMain;
import com.example.work.utils.Fiboracci;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Main4 extends BaseMain {

    private final static int testValue = 28;

    private static ReentrantLock lock = new ReentrantLock();
    private static Condition condition = lock.newCondition();

    public static void main(String[] args) {
        Thread thread = new Thread(() -> sumLock(testValue));
        thread.start();
        setValue();
        printInfo();
    }

    private static void setValue(){
        lock.lock();
        try {
            while (value == 0){
                condition.await();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            lock.unlock();
        }
    }

    private static void sumLock(int a){
        lock.lock();
        try{
            value = Fiboracci.fiboracci(a);
            condition.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
