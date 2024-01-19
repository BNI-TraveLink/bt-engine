package com.btengine.btlink.service;

import com.btengine.btlink.model.Balance;
import com.btengine.btlink.model.Login;
import com.btengine.btlink.model.Transaction;
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

    // ... other service methods

}

