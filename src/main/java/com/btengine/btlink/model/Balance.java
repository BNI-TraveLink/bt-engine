package com.btengine.btlink.model;
import lombok.*;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Table(name="balance",schema = "bt-link")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Balance {

    @Id
    @Column(name="sk_balance")
    private UUID skBalance;

    @Column(name = "fk_customer")
    private UUID fkCustomer;

    @Column(name = "balance")
    private BigDecimal balance;

    private LocalDateTime createdAt;
    private LocalDateTime  updatedAt;
}
