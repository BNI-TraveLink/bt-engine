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

    @Column
    private BigDecimal initialBalance;

    @Column
    private BigDecimal finalBalance;

    @Column
    private String val;

    @Column
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "fk_balance", nullable = false)
    private Balance balance;

}
