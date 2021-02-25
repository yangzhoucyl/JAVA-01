package com.example.database.model;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductInfo implements Serializable {
    /**
    * 商品id
    */
    private Integer productId;

    /**
    * 商品名
    */
    private String productName;

    /**
    * 商品描述
    */
    private String productDescribe;

    /**
    * 商品价格
    */
    private BigDecimal productPrice;

    /**
    * 所属商户
    */
    private Integer merchantId;

    private static final long serialVersionUID = 1L;
}