package com.btengine.btlink.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;



//@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "customer",schema = "bt-link")
public class Customer {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String skCustomer;

    private String name;

    @ManyToOne
    @JoinColumn(name = "fk_login")
    private Login login;
}
