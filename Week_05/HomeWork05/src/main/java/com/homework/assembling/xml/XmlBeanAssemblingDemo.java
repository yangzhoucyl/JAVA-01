package com.homework.assembling.xml;

import com.homework.aop.AopBean;
import com.homework.aop.BaseMethod;
import com.homework.assembling.xml.bean.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class XmlBeanAssemblingDemo {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Student student = (Student) context.getBean("student1");
        System.out.println(student.toString());
        BaseMethod aopBean = context.getBean(BaseMethod.class);
        aopBean.print();
    }
}
