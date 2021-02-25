   package com.example.database.dao;

import com.example.database.model.Order;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderMapper {
    /**
     * delete by primary key
     * @param orderId primaryKey
     * @return deleteCount
     */
    int deleteByPrimaryKey(Integer orderId);

    /**
     * insert record to table
     * @param record the record
     * @return insert count
     */
    int insert(Order record);

    /**
     * insert record to table selective
     * @param record the record
     * @return insert count
     */
    int insertSelective(Order record);

    /**
     * select by primary key
     * @param orderId primary key
     * @return object by primary key
     */
    Order selectByPrimaryKey(Integer orderId);

    /**
     * update record selective
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(Order record);

    /**
     * update record
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(Order record);

    int batchInsert(@Param("list") List<Order> list);
}