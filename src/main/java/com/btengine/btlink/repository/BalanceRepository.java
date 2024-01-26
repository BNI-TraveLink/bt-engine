package com.btengine.btlink.repository;


import com.btengine.btlink.model.Balance;
import com.btengine.btlink.model.BalanceHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface BalanceRepository extends JpaRepository<Balance, String> {
    @Query("SELECT b FROM Balance b WHERE b.skBalance = :balanceId")
    Balance findBalanceHistoryByBalanceId(@Param("balanceId") UUID balanceId);


    @Query(value = "select b.*  from \"bt-link\".balance b " +
            "inner join \"bt-link\".customer c on b.fk_customer = c.sk_customer " +
            "inner join \"bt-link\".login l on c.fk_login = l.sk_login " +
            "where l.user_id = :userid" ,nativeQuery = true)
    Balance getBalanceAllByUserId(@Param("userid") String userid);


    @Query(value = "select b.balance  from \"bt-link\".balance b " +
            "inner join \"bt-link\".customer c on b.fk_customer = c.sk_customer " +
            "inner join \"bt-link\".login l on c.fk_login = l.sk_login " +
            "where l.user_id = :userid" ,nativeQuery = true)
    BigDecimal getBalanceByUserId(@Param("userid") String userid);
}

