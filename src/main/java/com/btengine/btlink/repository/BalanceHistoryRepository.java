package com.btengine.btlink.repository;

import com.btengine.btlink.model.BalanceHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface BalanceHistoryRepository extends JpaRepository<BalanceHistory, String> {
    @Query("SELECT bh FROM BalanceHistory bh WHERE bh.balance.skBalance = :fkBalance")
    List<BalanceHistory> findBalanceHistoryByFkBalance(@Param("fkBalance") UUID fkBalance);
}
