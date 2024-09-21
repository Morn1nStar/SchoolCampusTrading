package com.campususedtrading.service.Impl;

import com.campususedtrading.mapper.CategoryMapper;
import com.campususedtrading.pojo.Category;
import com.campususedtrading.service.CategoryService;
import com.campususedtrading.util.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public List<Category> findAll() {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");

        return categoryMapper.findAll();
    }
}
