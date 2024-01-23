package com.btengine.btlink.controller;

import com.btengine.btlink.model.BalanceHistory;
import com.btengine.btlink.service.BalanceHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

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

    @GetMapping("/{fkBalance}")
    public ResponseEntity<?> findBalanceHistoryByFkBalance(@PathVariable UUID fkBalance) {
        try {
            List<BalanceHistory> balanceHistories = balanceHistoryService.findBalanceHistoryByFkBalance(fkBalance);
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(balanceHistories);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .contentType(MediaType.TEXT_PLAIN)
                    .body("Error retrieving balance history: " + e.getMessage());
        }
    }

    @PostMapping("/addBalanceHistory")
    public ResponseEntity<?> addBalanceHistory(@RequestParam BigDecimal initialBalance, @RequestParam String val, @RequestParam UUID fkBalance){
        try {
            balanceHistoryService.addBalanceHistory(initialBalance, val, fkBalance);
            return ResponseEntity.status(HttpStatus.CREATED).body("Balance History added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Registration failed: " + e.getMessage());
        }
    }
}
