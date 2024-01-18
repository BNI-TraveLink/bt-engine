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
@Table(name = "customer",schema = "bt-link")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID skCustomer;

    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name = "fk_login", nullable = false)
    private Login login;
}
