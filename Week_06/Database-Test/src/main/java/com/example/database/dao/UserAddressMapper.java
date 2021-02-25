package com.example.database.dao;

import com.example.database.model.UserAddress;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserAddressMapper {
    /**
     * delete by primary key
     * @param userAddressId primaryKey
     * @return deleteCount
     */
    int deleteByPrimaryKey(Integer userAddressId);

    /**
     * insert record to table
     * @param record the record
     * @return insert count
     */
    int insert(UserAddress record);

    /**
     * insert record to table selective
     * @param record the record
     * @return insert count
     */
    int insertSelective(UserAddress record);

    /**
     * select by primary key
     * @param userAddressId primary key
     * @return object by primary key
     */
    UserAddress selectByPrimaryKey(Integer userAddressId);

    /**
     * update record selective
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(UserAddress record);

    /**
     * update record
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(UserAddress record);

    int batchInsert(@Param("list") List<UserAddress> list);
}