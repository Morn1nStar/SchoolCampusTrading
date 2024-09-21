package com.campususedtrading.controller;

import com.campususedtrading.pojo.Category;
import com.campususedtrading.pojo.Result;
import com.campususedtrading.service.CategoryService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@Validated
@CrossOrigin
public class CategoryController {

    private CategoryService categoryService;

    // 文章分类列表
    @GetMapping
    public Result<List<Category>> findAll() {
        List<Category> categories = categoryService.findAll();
        return Result.success(categories);
    }

}
