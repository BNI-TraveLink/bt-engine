package com.btengine.btlink.service;

import com.btengine.btlink.model.Balance;
import com.btengine.btlink.model.BalanceHistory;
import com.btengine.btlink.repository.BalanceHistoryRepository;
import com.btengine.btlink.repository.BalanceRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class BalanceHistoryService {
    @Autowired
    BalanceRepository balanceRepository;

    @Autowired
    BalanceHistoryRepository balanceHistoryRepository;

    @Autowired
    public BalanceHistoryService(BalanceHistoryRepository balanceHistoryRepository) {
        this.balanceHistoryRepository = balanceHistoryRepository;
    }

    @Transactional
    public List<BalanceHistory> getAllBalanceHistories() {
        return balanceHistoryRepository.findAll();
    }

    @Transactional
    public BigDecimal addBalanceHistory(BigDecimal initialBalance, String val, UUID fkBalance) {
        BalanceHistory balanceHistory = new BalanceHistory();

        balanceHistory.setInitialBalance(initialBalance);

        Boolean isBelowZero = Integer.parseInt(val) < 0;
        BigDecimal value = new BigDecimal(val.substring(1));
        BigDecimal finalBalance;
        if (isBelowZero) {
            finalBalance = initialBalance.subtract(value);
        } else {
            finalBalance = initialBalance.add(value);
        }
        balanceHistory.setFinalBalance(finalBalance);

        balanceHistory.setVal(val);
        balanceHistory.setCreatedAt(LocalDateTime.now());

        Balance balance = balanceRepository.findBalanceHistoryByBalanceId(fkBalance);
        balanceHistory.setBalance(balance);

        balanceHistoryRepository.save(balanceHistory);

        return(finalBalance);
    }

    public List<BalanceHistory> findBalanceHistoryByFkBalance(UUID fkBalance) {
        return balanceHistoryRepository.findBalanceHistoryByFkBalance(fkBalance);
    }

}
