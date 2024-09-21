package com.campususedtrading.service;

import com.campususedtrading.pojo.PageBean;
import com.campususedtrading.pojo.Product;

public interface ProductService {

    void addProduct(Product product);

    Product getProduct(int id);

    PageBean<Product> list(Integer pageNUm, Integer pageSize, Integer categoryId,String state,Integer ownerId);

    void deleteProduct(int id);

    void updateProduct(Integer id, String state);
}
