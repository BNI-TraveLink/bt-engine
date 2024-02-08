package com.btengine.btlink.service;

import com.btengine.btlink.model.*;
import com.btengine.btlink.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PaymentService {

    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    LoginRepository loginRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    FacilityServiceRepository facilityServiceRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    public PaymentService (PaymentRepository paymentRepository) {this.paymentRepository = paymentRepository;}

    public List<Payment> getAllPayment() {
        return paymentRepository.findAll();
    }

    public Optional<Payment> getPaymentBySkPayment(UUID skPayment) {
        return paymentRepository.findBySkPayment(skPayment);
    }

    @Autowired
    BalanceService balanceService;

    @Transactional(rollbackFor = Exception.class) // Rollback for any exception
    public String savePayment(String userId, String serviceName, String departure,
                               String destination, Integer amount, BigDecimal totalPrice) {
        // Lakukan validasi atau logika bisnis jika diperlukan sebelum menyimpan
        //insert ke trands

        Transaction transaction = new Transaction();
        Payment payment = new Payment();

        Optional<Login> login = loginRepository.findUserByUserId(userId);
        Optional<Customer> customer = customerRepository.findcustomerByFkLogin(login.get().getSkLogin());
        FacilityService facility = facilityServiceRepository.findServicebyServiceName(serviceName);

        transaction.setCustomer(customer.get());
        transaction.setCreatedAt(LocalDateTime.now());
        transaction.setExpiredAt(LocalDateTime.of(LocalDate.now(), LocalTime.MAX));
        transaction.setActive(false);
        transaction.setService(facility);
        transaction.setDeparture(departure);
        transaction.setDestination(destination);
        transaction.setAmount(amount);

        payment.setFkTransaction(transaction);
        payment.setFkCustomer(customer.get());
        payment.setPrice(totalPrice);
        payment.setActive(false);
        payment.setCreatedAt(LocalDateTime.now());
        payment.setUpdatedAt(null);

        try {
            transactionRepository.save(transaction);
            paymentRepository.save(payment);
            String latestOrderId = paymentRepository.findLatestOrderIdByFkCustomer(customer.get().getSk_customer());
            return latestOrderId;
        } catch (Exception e) {
            // Rollback will be performed automatically if an exception occurs
            throw new RuntimeException("Error saving payment", e);
        }

    }
    @Transactional
    public void updatePayment(Long orderId, String userid, String val) {
        try {
            balanceService.updateBalance(userid, val);

            Payment payment = paymentRepository.getPaymentByOrderId(orderId);
            if (payment != null) {
                payment.setActive(true);
                payment.setUpdatedAt(LocalDateTime.now());
//                (Timestamp.valueOf(LocalDateTime.now()).toLocalDateTime());
                payment.getFkTransaction().setActive(true);
                payment.getFkTransaction().setUpdatedAt(LocalDateTime.now());
                payment.getFkTransaction().setOrderId(orderId);
                paymentRepository.save(payment);

            }
        } catch (Exception e) {
            // Handle the exception and potentially rethrow it
            // to trigger a rollback
            throw new RuntimeException("Error saving Update payment " + e.getMessage(), e);
        }
    }

}
