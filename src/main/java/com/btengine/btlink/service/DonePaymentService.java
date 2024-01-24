package com.btengine.btlink.service;

import com.btengine.btlink.model.Balance;
import com.btengine.btlink.model.Payment;
import com.btengine.btlink.model.Transaction;
import com.btengine.btlink.repository.DonePaymentRepository;
import com.btengine.btlink.repository.PaymentRepository;
import com.btengine.btlink.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class DonePaymentService {


    private DonePaymentRepository repository;  // Inject the repository

    public DonePaymentService(DonePaymentRepository repository) {
        this.repository = repository;
    }

    @Autowired
    BalanceService balanceService;

    public Optional<Payment> getPaymentByOrderId(Long orderId) {
        return repository.findPaymentByOrderId(orderId);  // Call the repository method
    }


    @Transactional
    public void updateDonePayment(Long orderId, UUID balanceId, String val) {
        try {
            balanceService.updateBalance(balanceId, val);

            Optional<Payment> paymentOptional = repository.findPaymentByOrderId(orderId);
            if (paymentOptional.isPresent()) {
                Payment payment = paymentOptional.get();
                payment.setActive(true);
                payment.setUpdatedAt(LocalDateTime.now());
//                (Timestamp.valueOf(LocalDateTime.now()).toLocalDateTime());
                payment.getFkTransaction().setActive(true);
                payment.getFkTransaction().setUpdatedAt(LocalDateTime.now());
                repository.save(payment);

            }
        } catch (Exception e) {
            // Handle the exception and potentially rethrow it
            // to trigger a rollback
            throw new RuntimeException("Error saving payment", e);
        }
    }



}
