package com.btengine.btlink.model;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "transaction", schema = "bt-link")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID )
    @Column(name="sk_transaction")
    private UUID skTransaction;

    @ManyToOne
    @JoinColumn(name = "fk_customer", nullable = false)
    private Customer fkCustomer;

    @ManyToOne
    @JoinColumn(name = "fk_service", nullable = false)
    private Service fkService;


    @Column(name = "created_at" , nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "expired_at")
    private LocalDateTime expiredAt;

    @Column(name = "is_active" , nullable = false)
    private boolean isActive;


    @Column(name = "departure" , nullable = false)
    private String departure;

    @Column(name = "destination" , nullable = false)
    private String destination;

    // Getters and setters for all fields
    // ...

}
