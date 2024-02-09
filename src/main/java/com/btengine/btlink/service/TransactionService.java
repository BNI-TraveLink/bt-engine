package com.btengine.btlink.service;

import com.btengine.btlink.model.*;
import com.btengine.btlink.repository.LoginRepository;
import com.btengine.btlink.repository.TicketRepository;
import com.btengine.btlink.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    TicketRepository ticketRepository;

//    @Transactional(readOnly = true) // Indicate read-only transaction for efficiency
    public List<Transaction> getAllTransaction() {
        List<Transaction> transactions =  transactionRepository.findAll();
        return transactions;
    }
    public Optional<Transaction> getTransactionBySkTransaction(UUID skTransaction) {
        return transactionRepository.findBySkTransaction(skTransaction);
    }

    // ... other service methods

    public List<Transaction> getTransactionsByCustomer(UUID fkCustomer) {
        return transactionRepository.findAllTransactionsByCustomer(fkCustomer);
    }

    public Optional<Transaction> findTransactionByOrderId(Long orderId) {
        return transactionRepository.findTransactionByPaymentOrderId(orderId);
    }

    public List<Transaction> findTransactionByUserId(String userId) {
        return transactionRepository.findTransactionByLoginUserId(userId);
    }

    @Transactional
    public Optional<Transaction> doneTransaction(UUID skTransaction){
        try{
            Optional<Transaction> transaction = transactionRepository.findBySkTransaction(skTransaction);

            transaction.get().setActive(false);
            transaction.get().setUpdatedAt(LocalDateTime.now());
            List <Ticket> tickets =  ticketRepository.findTicketsByFkTransaction(transaction.get().getSkTransaction());
            for (Ticket ticket : tickets){
                ticket.setIsActive(false);
                ticket.setUpdatedAt(LocalDateTime.now());
            }
            transactionRepository.save(transaction.get());
            ticketRepository.saveAll(tickets);
            return transaction;
        }catch (Exception e){
            throw new RuntimeException("Error saving Update payment " + e.getMessage(), e);
        }
    }
}

