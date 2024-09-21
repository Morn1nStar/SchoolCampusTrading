package com.campususedtrading.mapper;

import com.campususedtrading.pojo.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CategoryMapper {

    // 查询所有
    @Select("select * from categories")
    List<Category> findAll();

    @Select("select * from categories where id = #{id}")
    Category findById(int id);
}
