package com.btengine.btlink.controller;

import com.btengine.btlink.model.Balance;
import com.btengine.btlink.model.Customer;
import com.btengine.btlink.model.Stations;
import com.btengine.btlink.model.Transaction;

import com.btengine.btlink.service.StationsService;
import com.btengine.btlink.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin("*")
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

    @GetMapping("/{sk_transaction}")
    public Transaction getTransactionBySkTransaction(@PathVariable("sk_transaction") UUID skTransaction) {
        return transactionService.getTransactionBySkTransaction(skTransaction)
                .orElseThrow(() -> new RuntimeException("Transaksi tidak ditemukan untuk sk_transaction: " + skTransaction));
    }

    @GetMapping("/customer/{fkCustomer}")
    public ResponseEntity<List<Transaction>> getTransactionsByCustomer(@PathVariable UUID fkCustomer) {
        List<Transaction> transactions = transactionService.getTransactionsByCustomer(fkCustomer);
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/orderId/{order_id}")
    public Transaction findTransactionByPaymentOrderId(@PathVariable("order_id") Long orderId) {
        return transactionService.findTransactionByOrderId(orderId)
                .orElseThrow(() -> new RuntimeException("Transaksi tidak ditemukan untuk order_id: " + orderId));
    }
}