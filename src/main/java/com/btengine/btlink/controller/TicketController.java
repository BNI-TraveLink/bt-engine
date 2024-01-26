package com.btengine.btlink.controller;

import com.btengine.btlink.model.BalanceHistory;
import com.btengine.btlink.model.Login;
import com.btengine.btlink.model.Ticket;
import com.btengine.btlink.model.Transaction;
import com.btengine.btlink.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serial;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@CrossOrigin("*")
@RestController
@RequestMapping("/tickets")
public class TicketController {
    @Autowired
    TicketService ticketService;

    @GetMapping
    public List<Ticket> getAllTickets() {
        return ticketService.getAllTickets();
    }

    @GetMapping("/{fkTransaction}")
    public ResponseEntity<?> findTicketsByFkTransaction(@PathVariable UUID fkTransaction) {
        try {
            List<Ticket> tickets = ticketService.findTicketsByFkTransaction(fkTransaction);
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(tickets);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .contentType(MediaType.TEXT_PLAIN)
                    .body("Error retrieving balance history: " + e.getMessage());
        }
    }


    @PostMapping("/GenerateTicket/{orderId}")
    public List<Ticket> generateTicketByOrderId(@PathVariable("orderId") Long orderId) {
        return ticketService.generateTicket(orderId);
    }
}
