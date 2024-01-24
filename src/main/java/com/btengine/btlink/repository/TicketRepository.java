package com.btengine.btlink.repository;

import com.btengine.btlink.model.BalanceHistory;
import com.btengine.btlink.model.Payment;
import com.btengine.btlink.model.Ticket;
import com.btengine.btlink.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TicketRepository extends JpaRepository<Ticket, String> {
    @Query("SELECT t FROM Ticket t WHERE t.transaction.skTransaction = :fkTransaction")
    List<Ticket> findTicketsByFkTransaction(@Param("fkTransaction") UUID fkTransaction);

    @Query("SELECT p FROM Payment p WHERE p.orderId = :orderId")  // Use JPQL syntax for entities
    Optional<Payment> findTicketByOrderId(@Param("orderId") Long orderId);  // Use Optional for clarity

//     Inside your repository interface for the Transaction entity
//    @Query(value = "SELECT t.amount FROM transaction t JOIN t.ticket tk JOIN tk.payment p WHERE p.orderId = :orderId", nativeQuery = true)
//    Optional <Ticket> getAmountByOrderId(@Param("orderId") Long orderId);

//    @Query(value = "SELECT tk FROM Transaction t JOIN t.ticket tk JOIN tk.payment p WHERE p.orderId = :orderId", nativeQuery = true)
//    Optional<Ticket> getAmountByOrderId(@Param("orderId") Long orderId);

    // In your repository for Transaction:
//    @Query(value = "SELECT t.amount FROM Transaction t JOIN Payment p ON t.skTransaction = p.fkTransaction WHERE p.orderId = :orderId", nativeQuery = true)
//    Optional<Ticket> getAmountByOrderId(@Param("orderId") Long orderId);

//    @Query(value = "SELECT t.amount FROM Ticket t JOIN t.transaction transaction JOIN transaction.payment payment WHERE payment.orderId = :orderId", nativeQuery = true)
//    Integer getAmountByOrderId(@Param("orderId") Long orderId);

    @Query(value = "SELECT " +
            "t.sk_transaction " +
            ",t.fk_customer  " +
            ",t.created_at   " +
            ",t.expired_at   " +
            ",t.is_active    " +
            ",t.fk_service   " +
            ",t.departure    " +
            ",t.destination  " +
            ",t.amount       " +
            ",t.updated_at  " +
            "FROM \"bt-link\".transaction t " +
            "INNER JOIN \"bt-link\".payment p ON p.fk_transaction = t.sk_transaction " +
            "WHERE p.order_Id = :orderId", nativeQuery = true)
    List<Object[]> getTransactionByOrderId(@Param("orderId") Long orderId);


}
