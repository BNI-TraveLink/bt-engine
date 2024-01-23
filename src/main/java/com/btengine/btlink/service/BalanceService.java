package com.btengine.btlink.service;

import com.btengine.btlink.model.Balance;
import com.btengine.btlink.model.BalanceHistory;
import com.btengine.btlink.repository.BalanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    BalanceHistoryService balanceHistoryService;

    // Inject dependencies in constructor

//    @Transactional
//    public Balance createBalance(UUID customerId, BigDecimal initialBalance) {
//        Balance balance = new Balance();
//        balance.setSkBalance(UUID.randomUUID().toString()); // Generate unique ID
//        balance.setFkCustomer(customerId.toString());
//        balance.setBalance(initialBalance);
//        return balanceRepository.save(balance);
//    }

    public Balance getBalanceById(UUID balanceId) {
        return balanceRepository.findBalanceHistoryByBalanceId(balanceId);
    }

    // Add other service methods for balance-related logic

    @Transactional
//    public void updateBalance(UUID balanceId, BigDecimal newBalance) {
    public void updateBalance(UUID balanceId, String val) {
        Balance balance = getBalanceById(balanceId);

        BigDecimal finalBalance = balanceHistoryService.addBalanceHistory(balance.getBalance(), val, balance.getSkBalance());

        balance.setBalance(finalBalance);
        balanceRepository.save(balance);
    }

    @Transactional(readOnly = true) // Indicate read-only transaction for efficiency
    public List<Balance> getAllBalances() {
        return balanceRepository.findAll();
    }
    // ... other service methods
}