package com.campususedtrading.controller;

import com.campususedtrading.pojo.Result;
import com.campususedtrading.pojo.Transaction;
import com.campususedtrading.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
@CrossOrigin
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/purchase")
    public Result purchaseProduct(@RequestParam int productId) {
        transactionService.createTransaction(productId);
        return Result.success();
    }
}
