package com.btengine.btlink.repository;

import com.btengine.btlink.model.FacilityService;
import com.btengine.btlink.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, UUID> {
    List<Payment> findAll();
    Optional<Payment> findBySkPayment(UUID skPayment);

    @Query(value = "SELECT s FROM Service s WHERE s.name = :name",nativeQuery = true)
    FacilityService findServicebyServiceName(@Param("name") String name);

    @Query(value = "select * from \"bt-link\".payment p where p.order_id = :order_id", nativeQuery = true)
    Payment getPaymentByOrderId(@Param("order_id") Long order_id);

    @Query(value = "SELECT p.order_Id FROM  \"bt-link\".Payment p " +
            "WHERE p.fk_Customer = :fkCustomer AND p.is_Active = false ORDER BY p.created_At DESC LIMIT 1"
            ,nativeQuery = true)
    String findLatestOrderIdByFkCustomer(@Param("fkCustomer") UUID fkCustomer);

//    @Query("SELECT p.orderId FROM Payment p WHERE p.fkCustomer.fkLogin = :fkLogin AND p.isActive = true ORDER BY p.createdAt DESC")
//    Long findLatestOrderIdByFkCustomer(@Param("fkLogin") Login fkLogin);

}

