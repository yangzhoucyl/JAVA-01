package com.example.work.utils;

/**
 * @author yangzhou
 */
public class Fiboracci {

    public static int fiboracci(int a) {
        if ( a < 2) {
            return 1;
        }
        return fiboracci(a-1) + fiboracci(a-2);
    }
}
