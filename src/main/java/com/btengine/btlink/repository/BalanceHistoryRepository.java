package com.btengine.btlink.repository;

import com.btengine.btlink.model.BalanceHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BalanceHistoryRepository extends JpaRepository<BalanceHistory, String> {
}
