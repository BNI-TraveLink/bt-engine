package com.btengine.btlink.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "balanceHistory", schema = "bt-link")
public class BalanceHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID skBalanceHistory;

    @Column(name = "initial_balance")
    private BigDecimal initialBalance;

    @Column(name = "final_balance")
    private BigDecimal finalBalance;

    @Column(name = "val")
    private String val;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "fk_balance", nullable = false)
    private Balance balance;

}
