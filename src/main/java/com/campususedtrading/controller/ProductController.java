package com.campususedtrading.controller;

import com.campususedtrading.pojo.PageBean;
import com.campususedtrading.pojo.Product;
import com.campususedtrading.pojo.Result;
import com.campususedtrading.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@Validated
@CrossOrigin
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public Result addProduct(@Validated @RequestBody Product product) {
        productService.addProduct(product);
        return Result.success();
    }

    @GetMapping("/detail")
    public Result<Product> getProduct(@RequestParam("id") int id) {
        Product product = productService.getProduct(id);
        if (product == null) {
            return Result.error("商品不存在");
        }
        return Result.success(product);
    }

    @GetMapping
    public Result<PageBean<Product>> getAllProduct(
            Integer pageNum,
            Integer pageSize,
            @RequestParam(required = false) Integer categoryId,
            @RequestParam(required = false) String state,
            @RequestParam(required = false) Integer judge
            ) {
        PageBean<Product> pageBean = productService.list(pageNum, pageSize, categoryId, state, judge);
        return Result.success(pageBean);
    }

    @PutMapping
    public Result updateProduct(@RequestParam("id") int id,@RequestParam("state") String state) {
        productService.updateProduct(id,state);
        return Result.success();
    }

    @DeleteMapping
    public Result deleteProduct(@RequestParam("id") int id) {
        productService.deleteProduct(id);
        return Result.success();
    }
}
