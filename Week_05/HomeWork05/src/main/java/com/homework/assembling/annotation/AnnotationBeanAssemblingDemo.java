package com.homework.assembling.annotation;

import com.homework.assembling.annotation.bean.Student;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author yangzhou
 */

public class AnnotationBeanAssemblingDemo {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext("com.homework.assembling.annotation.bean");
        Student student = (Student) applicationContext.getBean("student001");
        student.setAge("11");
        student.setId(111111L);
        System.out.println(student.toString());
    }
}
