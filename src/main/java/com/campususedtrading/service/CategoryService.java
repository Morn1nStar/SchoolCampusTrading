package com.campususedtrading.service;

import com.campususedtrading.pojo.Category;

import java.util.List;

public interface CategoryService {
    // 商品分类列表
    List<Category> findAll();
}
