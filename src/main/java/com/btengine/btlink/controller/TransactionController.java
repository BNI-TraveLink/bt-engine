package com.btengine.btlink.controller;

import com.btengine.btlink.model.Balance;
import com.btengine.btlink.model.Stations;
import com.btengine.btlink.model.Transaction;

import com.btengine.btlink.service.StationsService;
import com.btengine.btlink.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    // ... other methods (createTransaction, getTransaction, etc.)

    @GetMapping
    public ResponseEntity<List<Transaction>> getAllTransaction() {
        List<Transaction> transactions = transactionService.getAllTransaction();
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(transactions);
    }

}