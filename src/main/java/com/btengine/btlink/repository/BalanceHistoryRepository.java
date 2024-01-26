package com.btengine.btlink.repository;

import com.btengine.btlink.model.BalanceHistory;
import org.postgresql.core.NativeQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BalanceHistoryRepository extends JpaRepository<BalanceHistory, String> {
    @Query("SELECT bh FROM BalanceHistory bh WHERE bh.balance.skBalance = :fkBalance")
    List<BalanceHistory> findBalanceHistoryByFkBalance(@Param("fkBalance") UUID fkBalance);

    @Query (value = "select bh.* from \"bt-link\".balance_history bh " +
           " inner join \"bt-link\".balance b on bh.fk_balance = b.sk_balance " +
           " inner join \"bt-link\".customer c on b.fk_customer = c.sk_customer " +
            " inner join \"bt-link\".login l on c.fk_login = c.fk_login " +
           " where l.user_id = :userid "+
            "order by bh.created_at desc", nativeQuery = true)
    List<BalanceHistory> findBalanceHistoryByUserId(@Param("userid") String userid);
}
