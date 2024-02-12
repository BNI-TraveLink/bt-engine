package com.btengine.btlink.repository;
import com.btengine.btlink.model.Customer;
import com.btengine.btlink.model.Payment;
import com.btengine.btlink.model.Stations;
import com.btengine.btlink.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
    List<Transaction> findAll();
    Optional<Transaction> findBySkTransaction(UUID skTransaction);

        @Query(value = "SELECT * FROM \"bt-link\".\"transaction\" WHERE fk_customer = :fkCustomer", nativeQuery = true)
    List<Transaction> findAllTransactionsByCustomer(@Param("fkCustomer") UUID fkCustomer);

    @Query(value = "SELECT t.* FROM \"bt-link\".\"transaction\" t JOIN \"bt-link\".payment p ON t.sk_transaction = p.fk_transaction WHERE p.order_id = :orderId", nativeQuery = true)
    Optional<Transaction> findTransactionByPaymentOrderId(@Param("orderId") Long orderId);

    @Query(value = "SELECT *\n" +
            "FROM \"bt-link\".transaction t\n" +
            "INNER JOIN \"bt-link\".customer c ON t.fk_customer = c.sk_customer\n" +
            "INNER JOIN \"bt-link\".login l ON c.fk_login = l.sk_login\n" +
            "WHERE l.user_id = :userId AND t.order_id IS NOT NULL", nativeQuery = true)
    List<Transaction> findTransactionByLoginUserId(@Param("userId") String userId);
}