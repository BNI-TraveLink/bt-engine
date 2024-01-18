package com.btengine.btlink.service;

import com.btengine.btlink.model.BalanceHistory;
import com.btengine.btlink.repository.BalanceHistoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class BalanceHistoryService {
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
    public void addBalanceHistory(BigDecimal initialBalance, BigDecimal finalBalance, String val) {
        // add data to balance history
    }

}
