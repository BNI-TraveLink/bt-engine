package com.btengine.btlink.repository;


import com.btengine.btlink.model.Balance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BalanceRepository extends JpaRepository<Balance, String> {
    // Add custom query methods here if needed
}

