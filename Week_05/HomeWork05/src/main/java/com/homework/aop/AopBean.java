package com.homework.aop;

import com.homework.assembling.xml.bean.Student;
import lombok.Data;

import javax.annotation.Resource;


/**
 * @author yangzhou
 */
@Data
public class AopBean implements BaseMethod{

    @Resource(name = "student1")
    private Student student;

    @Override
    public void print() {
        System.out.println(this.toString());
    }
}
