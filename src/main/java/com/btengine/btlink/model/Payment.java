package com.btengine.btlink.model;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(schema = "bt-link", name = "payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="sk_Payment")
    public UUID skPayment;

//    @Column(name = "order_id", nullable = false, unique = true, columnDefinition = "SERIAL")
//    private Integer orderId;

    @Column(name = "order_id", nullable = false, unique = true, insertable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @ManyToOne
    @JoinColumn(name = "fk_transaction", nullable = false)
    private Transaction fkTransaction;

    @OneToOne
    @JoinColumn(name = "fk_customer", nullable = false)
    private Customer fkCustomer;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "is_active" , nullable = false)
    private boolean isActive;

    @Column(name = "created_at" , nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
