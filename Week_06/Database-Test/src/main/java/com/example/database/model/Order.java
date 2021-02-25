package com.example.database.model;

import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order implements Serializable {
    /**
    * 订单id
    */
    private Integer orderId;

    /**
    * 订单号
    */
    private String orderNo;

    /**
    * 创建时间
    */
    private Date createTime;

    /**
    * 更新时间
    */
    private Date updateTime;

    /**
    * 用户id
    */
    private Integer userId;

    /**
    * 订单状态
    */
    private Integer orderStatus;

    /**
    * 收货地址id
    */
    private Integer orderAddressId;

    /**
    * 描述
    */
    private String remarks;

    private static final long serialVersionUID = 1L;
}