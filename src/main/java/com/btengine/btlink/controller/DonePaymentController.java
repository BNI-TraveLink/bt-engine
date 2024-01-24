package com.btengine.btlink.controller;

import com.btengine.btlink.model.Balance;
import com.btengine.btlink.repository.BalanceRepository;
import com.btengine.btlink.service.BalanceService;
import com.btengine.btlink.service.DonePaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController

@RequestMapping(value = "DonePayment")
public class DonePaymentController {


    @Autowired
    private DonePaymentService donePaymentService; // Nama service harus sesuai


    @PutMapping("/updateDonePayment")
    public ResponseEntity<?> updateDonePayment(@RequestParam Long orderId, @RequestParam UUID balanceId, @RequestParam String val) {
        try {
            donePaymentService.updateDonePayment(orderId, balanceId, val);
            return ResponseEntity.status(HttpStatus.CREATED).body("DonePayment updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("DonePayment updated failed: " + e.getMessage());
        }
    }
}
