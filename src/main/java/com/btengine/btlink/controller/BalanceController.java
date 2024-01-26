package com.btengine.btlink.controller;
import com.btengine.btlink.model.Balance;
import com.btengine.btlink.repository.BalanceRepository;
import com.btengine.btlink.service.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/balance")
public class BalanceController {

    @Autowired
    private BalanceService balanceService; // Nama service harus sesuai

    @GetMapping
    public ResponseEntity<List<Balance>> getAllBalances() {
        List<Balance> balances = balanceService.getAllBalances();
        return ResponseEntity.ok(balances);
    }

    @PostMapping("/updateBalance")
    public ResponseEntity<?> updateBalance(@RequestParam UUID skBalance, @RequestParam String val) {
        try {
            balanceService.updateBalance(skBalance, val);
            return ResponseEntity.status(HttpStatus.CREATED).body("Balance updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Registration failed: " + e.getMessage());
        }
    }

    @GetMapping("/getBalanceByUserId/{userid}")
    public BigDecimal getBalanceByUserId(@PathVariable String userid){

            return balanceService.getbalanceByUserID(userid);

    }
}