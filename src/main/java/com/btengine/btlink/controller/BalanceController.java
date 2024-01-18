package com.btengine.btlink.controller;
import com.btengine.btlink.model.Balance;
import com.btengine.btlink.repository.BalanceRepository;
import com.btengine.btlink.service.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

@RestController

@RequestMapping(value = "balance")
public class BalanceController {

    @Autowired
    private BalanceService balanceService; // Nama service harus sesuai

    @GetMapping
    public ResponseEntity<List<Balance>> getAllBalances() {
        List<Balance> balances = balanceService.getAllBalances();
        return ResponseEntity.ok(balances);
    }
}