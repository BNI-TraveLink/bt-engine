package com.btengine.btlink.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;


//@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "customer", schema = "bt-link")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "sk_customer", nullable = false)
    private UUID sk_customer;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "fk_login", nullable = false)
    private Login fkLogin;
}
