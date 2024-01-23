package com.btengine.btlink.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "ticket",schema = "bt-link")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public UUID skTicket;

    @Column(name = "ticket_number", nullable = false, unique = true, insertable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long ticketNumber;

    @Column(name = "is_active")
    public Boolean isActive;

    @Column(name = "created_at")
    public LocalDateTime createdAt;

    @Column(name = "updated_at")
    public LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "fk_transaction", nullable = false)
    public Transaction transaction;
}
