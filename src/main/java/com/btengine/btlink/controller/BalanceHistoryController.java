package com.btengine.btlink.controller;

import com.btengine.btlink.model.BalanceHistory;
import com.btengine.btlink.service.BalanceHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "balanceHistory")
public class BalanceHistoryController {
    private final BalanceHistoryService balanceHistoryService;

    @Autowired
    public BalanceHistoryController(BalanceHistoryService balanceHistoryService) {
        this.balanceHistoryService = balanceHistoryService;
    }

    @GetMapping
    public List<BalanceHistory> getAllBalanceHistories() {
        return balanceHistoryService.getAllBalanceHistories();
    }
}
