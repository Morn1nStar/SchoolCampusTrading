package com.campususedtrading.service.Impl;

import com.campususedtrading.mapper.ProductMapper;
import com.campususedtrading.mapper.TransactionMapper;
import com.campususedtrading.pojo.Product;
import com.campususedtrading.pojo.Transaction;
import com.campususedtrading.service.TransactionService;
import com.campususedtrading.util.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
public class TransactionServiceImp implements TransactionService {

    @Autowired
    private TransactionMapper transactionMapper;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public void createTransaction(Integer productId) {
        Product product = productMapper.getProduct(productId);
        // 检查商品是否存在
        if (product == null) {
            throw new IllegalArgumentException("Product with ID " + productId + " does not exist.");
        }

        // 检查商品是否已经被售出
        if (product.getState().equals("已售出")) {
            throw new IllegalArgumentException("Product with ID " + productId + " has already been sold.");
        }

        Transaction transaction = new Transaction();
        transaction.setProductId(productId);

        Integer sellerId = product.getOwnerId();
        BigDecimal price = product.getPrice();
        transaction.setSellerId(sellerId);
        transaction.setPrice(price);

        Map<String,Object> map = ThreadLocalUtil.get();
        Integer buyerId = (Integer) map.get("id");
        transaction.setBuyerId(buyerId);

        // 检查购买商品者和出售商品者非同一人
        if (buyerId.equals(sellerId)) {
            throw new IllegalArgumentException("Have the same sellerId.");
        }

        transactionMapper.addTransaction(transaction);

        productMapper.updateProduct(productId,"已售出");
    }
}
