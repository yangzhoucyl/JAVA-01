package com.example.database.dao;

import com.example.database.model.ProductInfo;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ProductInfoMapper {
    /**
     * delete by primary key
     * @param productId primaryKey
     * @return deleteCount
     */
    int deleteByPrimaryKey(Integer productId);

    /**
     * insert record to table
     * @param record the record
     * @return insert count
     */
    int insert(ProductInfo record);

    /**
     * insert record to table selective
     * @param record the record
     * @return insert count
     */
    int insertSelective(ProductInfo record);

    /**
     * select by primary key
     * @param productId primary key
     * @return object by primary key
     */
    ProductInfo selectByPrimaryKey(Integer productId);

    /**
     * update record selective
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(ProductInfo record);

    /**
     * update record
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(ProductInfo record);

    int batchInsert(@Param("list") List<ProductInfo> list);
}