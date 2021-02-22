package com.homework.aop;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @author yangzhou
 */
public class StudentAop {

    public void before(){
        System.out.println(" aop before start");
    }

    public void after(){
        System.out.println(" aop after start");

    }

    public void around(ProceedingJoinPoint joinPoint){
        System.out.println("around start");
        try {
            joinPoint.proceed();
            System.out.println("around finish");

        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

    }
}
