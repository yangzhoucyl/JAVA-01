package com.example.database.dao;

import com.example.database.model.OrderProduct;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderProductMapper {
    /**
     * delete by primary key
     * @param orderProductId primaryKey
     * @return deleteCount
     */
    int deleteByPrimaryKey(Integer orderProductId);

    /**
     * insert record to table
     * @param record the record
     * @return insert count
     */
    int insert(OrderProduct record);

    /**
     * insert record to table selective
     * @param record the record
     * @return insert count
     */
    int insertSelective(OrderProduct record);

    /**
     * select by primary key
     * @param orderProductId primary key
     * @return object by primary key
     */
    OrderProduct selectByPrimaryKey(Integer orderProductId);

    /**
     * update record selective
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(OrderProduct record);

    /**
     * update record
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(OrderProduct record);

    int batchInsert(@Param("list") List<OrderProduct> list);
}