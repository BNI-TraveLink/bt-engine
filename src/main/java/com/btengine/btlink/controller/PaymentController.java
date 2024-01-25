package com.btengine.btlink.controller;

import com.btengine.btlink.model.Payment;
import com.btengine.btlink.service.PaymentService;
//import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin("*")
@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/{sk_payment}")
    public ResponseEntity<Optional<Payment>> getPaymentBySkPayment(@PathVariable UUID sk_payment) {
        Optional<Payment> payment = paymentService.getPaymentBySkPayment(sk_payment);
        if (ObjectUtils.isEmpty(payment)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(null); // atau throw NotFoundException atau sejenisnya
        }
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(payment);
    }

    @GetMapping
    public ResponseEntity<List<Payment>> getAllPayment() {
        List<Payment> allPayment = paymentService.getAllPayment();
        if (allPayment.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(null); // atau throw NotFoundException atau sejenisnya
        }
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(allPayment);
    }

//    @PostMapping
//    public ResponseEntity<Payment> savePayment(
    @PostMapping
    public ResponseEntity<String> savePayment(
//                                               @RequestBody Payment payment,
                                               @RequestParam String userId,
                                               @RequestParam String serviceName,
                                               @RequestParam String departure,
                                               @RequestParam String destination,
                                               @RequestParam Integer amount,
                                               @RequestParam Integer totalPrice) {
        // Lakukan validasi atau logika bisnis jika diperlukan sebelum menyimpan
        String savedPayment = paymentService.savePayment(userId, serviceName, departure,
                destination, amount, BigDecimal.valueOf(totalPrice));

//        Payment savedPayment = paymentService.savePayment(payment, userId, serviceName, departure,
//                destination, amount);

        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(savedPayment);

    }
    // Metode DELETE, dll. dapat ditambahkan sesuai kebutuhan proyek
}
