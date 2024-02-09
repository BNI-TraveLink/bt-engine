package com.btengine.btlink.controller;

import com.btengine.btlink.model.*;

import com.btengine.btlink.service.StationsService;
import com.btengine.btlink.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
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

    @GetMapping("/userId/{user_id}")
    public ResponseEntity<?> findTransactionByLoginUserId(@PathVariable("user_id")   String userId) {
        try {
            List<Transaction> transactions = transactionService.findTransactionByUserId(userId);
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(transactions);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .contentType(MediaType.TEXT_PLAIN)
                    .body("Error retrieving User Id in Transaction: " + e.getMessage());
        }
    }

    @PostMapping("/doneTransaction")
    public ResponseEntity<?> donePayment(@RequestParam UUID skTransaction){
        try {
             Optional<Transaction> transaction= transactionService.doneTransaction(skTransaction);
            return  ResponseEntity.status(HttpStatus.CREATED).body(transaction);
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Transaction And Tickets Update failed: " + e.getMessage());
        }
    }
}