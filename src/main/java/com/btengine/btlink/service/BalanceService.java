package com.btengine.btlink.service;

import com.btengine.btlink.model.Balance;
import com.btengine.btlink.repository.BalanceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.UUID;
import java.math.BigDecimal;
import java.util.List;

@Service
public class BalanceService {

    private final BalanceRepository balanceRepository;

    public BalanceService(BalanceRepository balanceRepository) {
        this.balanceRepository = balanceRepository;
    }

    // Inject dependencies in constructor

//    @Transactional
//    public Balance createBalance(UUID customerId, BigDecimal initialBalance) {
//        Balance balance = new Balance();
//        balance.setSkBalance(UUID.randomUUID().toString()); // Generate unique ID
//        balance.setFkCustomer(customerId.toString());
//        balance.setBalance(initialBalance);
//        return balanceRepository.save(balance);
//    }

    public Balance getBalanceById(String balanceId) {
        Balance balance = balanceRepository.findById(balanceId).orElse(null);
        if (balance == null) {
            // Handle the case where balance is not found
            // You can either:

            // 1. Return a default value or empty object:
            // return new BalanceModel(); // Or create an empty BalanceModel

            // 2. Throw a different exception:
            throw new IllegalStateException("Balance not found with ID: " + balanceId);

            // 3. Log a warning and return null:
            // log.warn("Balance not found with ID: {}", balanceId);
            // return null;
        }
        return balance;
    }

    // Add other service methods for balance-related logic

    @Transactional
    public void updateBalance(String balanceId, BigDecimal newBalance) {
        Balance balance = getBalanceById(balanceId);
        balance.setBalance(newBalance);
        balanceRepository.save(balance);
    }
    @Transactional(readOnly = true) // Indicate read-only transaction for efficiency
    public List<Balance> getAllBalances() {
        return balanceRepository.findAll();
    }
    // ... other service methods
}