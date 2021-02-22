package com.homework.assembling.xml.bean;

import com.homework.aop.BaseMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yangzhou
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student{
    private long id;
    private String name;
    private String age;
    private String sex;

}
