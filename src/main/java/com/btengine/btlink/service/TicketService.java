package com.btengine.btlink.service;

import com.btengine.btlink.model.BalanceHistory;
import com.btengine.btlink.model.Ticket;
import com.btengine.btlink.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TicketService {
    @Autowired
    TicketRepository ticketRepository;

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    public List<Ticket> findTicketsByFkTransaction (UUID fkTransaction) {
        return ticketRepository.findTicketsByFkTransaction(fkTransaction);
    }
}
