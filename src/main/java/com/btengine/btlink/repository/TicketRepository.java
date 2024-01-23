package com.btengine.btlink.repository;

import com.btengine.btlink.model.BalanceHistory;
import com.btengine.btlink.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface TicketRepository extends JpaRepository<Ticket, String> {
    @Query("SELECT t FROM Ticket t WHERE t.transaction.skTransaction = :fkTransaction")
    List<Ticket> findTicketsByFkTransaction(@Param("fkTransaction") UUID fkTransaction);
}
