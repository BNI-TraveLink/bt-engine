package com.btengine.btlink.repository;
import com.btengine.btlink.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, String> {

}