package com.btengine.btlink.service;

import com.btengine.btlink.model.Payment;
import com.btengine.btlink.model.Ticket;
import com.btengine.btlink.model.Transaction;
import com.btengine.btlink.repository.PaymentRepository;
import com.btengine.btlink.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class TicketService {
    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    PaymentRepository paymentRepository;

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    public List<Ticket> findTicketsByFkTransaction (UUID fkTransaction) {
        return ticketRepository.findTicketsByFkTransaction(fkTransaction);
    }

    public List<Ticket> getTicketsByUserId (String userid){
        return  ticketRepository.getTicketsByUserId(userid);
    }

//    public List<Ticket> findTicketByOrderId (Integer orderId) {
//        return ticketRepository.findTicketByOrderId(orderId);
//    }
    public List<Ticket> generateTicket(Long orderId) {
        List<Ticket> tickets = new ArrayList<Ticket>();
//        Ticket ticket = new Ticket();

        Payment payment = paymentRepository.getPaymentByOrderId(orderId);
        Transaction transaction =payment.getFkTransaction();
        Integer amountTrans = transaction.getAmount();


        for(int i = 0; i<amountTrans; i++){
            Ticket ticket = new Ticket();
            ticket.setTransaction(transaction);
            ticket.setIsActive(true);
            ticket.setCreatedAt(LocalDateTime.now());

            tickets.add(ticket);


        }
        ticketRepository.saveAll(tickets);
        return tickets;
    }



}
