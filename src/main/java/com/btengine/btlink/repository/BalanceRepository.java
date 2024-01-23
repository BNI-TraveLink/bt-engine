package com.btengine.btlink.repository;


import com.btengine.btlink.model.Balance;
import com.btengine.btlink.model.BalanceHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface BalanceRepository extends JpaRepository<Balance, String> {
    @Query("SELECT b FROM Balance b WHERE b.skBalance = :balanceId")
    Balance findBalanceHistoryByBalanceId(@Param("balanceId") UUID balanceId);
}

