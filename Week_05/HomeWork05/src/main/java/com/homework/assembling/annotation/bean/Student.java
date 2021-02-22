package com.homework.assembling.annotation.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

/**
 * @author yangzhou
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Component(value = "student001")
public class Student {

    private long id;
    private String name;
    private String age;
    private String sex;
}
