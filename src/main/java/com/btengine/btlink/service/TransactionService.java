package com.btengine.btlink.service;

import com.btengine.btlink.model.*;
import com.btengine.btlink.repository.LoginRepository;
import com.btengine.btlink.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

//    @Transactional(readOnly = true) // Indicate read-only transaction for efficiency
    public List<Transaction> getAllTransaction() {
        List<Transaction> transactions =  transactionRepository.findAll();
        return transactions;
    }
    public Optional<Transaction> getTransactionBySkTransaction(UUID skTransaction) {
        return transactionRepository.findBySkTransaction(skTransaction);
    }

    // ... other service methods

    public List<Transaction> getTransactionsByCustomer(UUID fkCustomer) {
        return transactionRepository.findAllTransactionsByCustomer(fkCustomer);
    }

    public Optional<Transaction> findTransactionByOrderId(Long orderId) {
        return transactionRepository.findTransactionByPaymentOrderId(orderId);
    }

    public List<Transaction> findTransactionByUserId(String userId) {
        return transactionRepository.findTransactionByLoginUserId(userId);
    }
}

