package com.campususedtrading.service;

import com.campususedtrading.pojo.Transaction;

public interface TransactionService {
    // 购买商品
    void createTransaction(Integer productId);
}

