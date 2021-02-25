package com.example.database.model;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    /**
    * id
    */
    private Integer userId;

    /**
    * 登录名
    */
    private String userName;

    /**
    * 用户名
    */
    private String name;

    /**
    * 密码
    */
    private String passWord;

    /**
    * 手机号
    */
    private String phone;

    /**
    * 头像图片
    */
    private String headPictureUrl;

    /**
    * 账号
    */
    private String account;

    private static final long serialVersionUID = 1L;
}