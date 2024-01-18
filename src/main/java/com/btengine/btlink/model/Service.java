package com.btengine.btlink.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "service",schema = "bt-link")
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public UUID skService;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "name", nullable = false)
    private String name;

}
