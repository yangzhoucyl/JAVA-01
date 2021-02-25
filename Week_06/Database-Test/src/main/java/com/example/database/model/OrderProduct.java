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
public class OrderProduct implements Serializable {
    /**
    * 订单产品id
    */
    private Integer orderProductId;

    /**
    * 产品id
    */
    private Integer productId;

    /**
    * 订单id
    */
    private Integer orderId;

    /**
    * 产品数量
    */
    private Integer productNum;

    /**
    * 折扣
    */
    private Double discount;

    private static final long serialVersionUID = 1L;
}