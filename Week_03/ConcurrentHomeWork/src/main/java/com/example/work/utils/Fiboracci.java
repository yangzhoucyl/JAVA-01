package com.example.work.utils;

/**
 * @author yangzhou
 */
public class Fibo {

    private static int fibo(int a) {
        if ( a < 2) {
            return 1;
        }
        return fibo(a-1) + fibo(a-2);
    }
}
