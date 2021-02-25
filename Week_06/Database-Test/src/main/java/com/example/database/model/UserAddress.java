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
public class UserAddress implements Serializable {
    /**
    * id
    */
    private Integer userAddressId;

    /**
    * 用户id
    */
    private Integer userId;

    /**
    * 收货人
    */
    private String userName;

    /**
    * 联系人电话
    */
    private Integer phone;

    /**
    * 收货地址
    */
    private String address;

    private static final long serialVersionUID = 1L;
}