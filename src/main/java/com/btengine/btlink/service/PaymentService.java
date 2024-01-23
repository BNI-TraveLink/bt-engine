package com.btengine.btlink.service;

import com.btengine.btlink.model.Customer;
import com.btengine.btlink.model.Login;
import com.btengine.btlink.model.Payment;
import com.btengine.btlink.model.Transaction;
import com.btengine.btlink.model.FacilityService;
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

    @Transactional(rollbackFor = Exception.class) // Rollback for any exception
    public String savePayment(String userId, String serviceName, String departure,
                               String destination, Integer amount, BigDecimal price) {
        // Lakukan validasi atau logika bisnis jika diperlukan sebelum menyimpan
        //insert ke trands


            // Perform validation or business logic if necessary before saving

        Transaction transaction = new Transaction();
        Payment payment = new Payment();

        Optional<Login> login = loginRepository.findUserByUserId(userId);
        Optional<Customer> customer = customerRepository.findcustomerByFkLogin(login.get().getSkLogin());
        FacilityService facility = facilityServiceRepository.findServicebyServiceName(serviceName);

//        Optional<Login> login = loginRepository.findUserByUserId(userId);
//        Optional<Customer> customer = login.map(user -> customerRepository.findcustomerByFkLogin(user.getSkLogin().toString()))
//                .orElseThrow(() -> new RuntimeException("Customer not found"));
//        FacilityService facility = serviceRepository.findServicebyServiceName(serviceName);


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
        payment.setPrice(price);
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
        //ngambil payment setelah di save

//        get payment = membuat query where kondisi fk_customer = customer.get().getSkCustomer() AND payment.created_at = latest and is active = false =
//        PaymentRepository.saveByOrderId(transaction);

//        String latestOrderId = paymentRepository.findLatestOrderIdByFkCustomer(customer.get().getSk_customer());
//        return latestOrderId;

    }

}
