package com.btengine.btlink.repository;



import com.btengine.btlink.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.Optional;
import java.util.UUID;

public interface DonePaymentRepository extends JpaRepository<Payment, Long> {
//    @Query("SELECT * FROM \"bt-link\".\"payment\" WHERE order_id = :orderId")
//    Payment getPaymentByOrderId(@Param("orderId") Long orderId);
@Query("SELECT p FROM Payment p WHERE p.orderId = :orderId")  // Use JPQL syntax for entities
Optional<Payment> findPaymentByOrderId(@Param("orderId") Long orderId);  // Use Optional for clarity

}

