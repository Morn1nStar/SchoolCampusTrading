package com.campususedtrading.service.Impl;

import com.campususedtrading.mapper.CategoryMapper;
import com.campususedtrading.mapper.ProductMapper;
import com.campususedtrading.mapper.UserMapper;
import com.campususedtrading.pojo.*;
import com.campususedtrading.service.ProductService;
import com.campususedtrading.util.ThreadLocalUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    @Transactional
    public void addProduct(Product product) {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer ownerId = (Integer) map.get("id");
        product.setOwnerId(ownerId);

        Category category = categoryMapper.findById(product.getCategoryId());
        if (category == null) {
            throw new IllegalArgumentException("Category with ID " + product.getCategoryId() + " does not exist.");
        }

        User user = userMapper.findById(product.getOwnerId());
        if (user == null) {
            throw new IllegalArgumentException("User with ID " + product.getOwnerId() + " does not exist.");
        }

        productMapper.addProduct(product);

        if (product.getProductImages() != null) {
            for (ProductImage image : product.getProductImages()) {
                image.setProductId(product.getId());
            }
            productMapper.addProductImages(product.getProductImages()); // 使用批量插入方法
        }
    }

    @Override
    public Product getProduct(int id) {
        Product product = productMapper.getProduct(id);
        product.setProductImages(productMapper.getProductImages(id));
        return product;
    }

    @Override
    public PageBean<Product> list(Integer pageNUm, Integer pageSize, Integer categoryId, String state, Integer judge) {

        PageBean<Product> pageBean = new PageBean<>();

        PageHelper.startPage(pageNUm, pageSize);

        Map<String, Object> map = ThreadLocalUtil.get();
        Integer ownerId = (Integer) map.get("id");

        List<Product> products = productMapper.list(categoryId, state, judge, ownerId);
        Page<Product> page = (Page<Product>) products;

        for (Product product : page.getResult()) {
            List<ProductImage> productImages = productMapper.getProductImages(product.getId());
            product.setProductImages(productImages);
        }

        pageBean.setTotal(page.getTotal());
        pageBean.setItems(page.getResult());

        return pageBean;

    }

    @Override
    public void deleteProduct(int id) {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer ownerId = (Integer) map.get("id");
        productMapper.deleteProduct(id,ownerId);
    }

    @Override
    public void updateProduct(Integer id, String state) {
        productMapper.updateProduct(id,state);
    }


}
