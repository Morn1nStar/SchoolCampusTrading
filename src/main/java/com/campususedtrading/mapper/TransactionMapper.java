package com.campususedtrading.mapper;

import com.campususedtrading.pojo.Transaction;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TransactionMapper {

    @Insert("insert into transactions(product_id,buyer_id,seller_id,price,created_time) " +
            "values (#{productId},#{buyerId}, #{sellerId}, #{price}, NOW())")
    void addTransaction(Transaction transaction);
}
