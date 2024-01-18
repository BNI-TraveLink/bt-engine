package com.btengine.btlink.model;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.*;

import java.util.UUID;

//@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "login",schema = "bt-link")
public class Login {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public UUID skLogin;

    @Column
    public String accountNumber;
    @Column
    public String userId;
    @Column
    public String transactionPassword;
    @Column
    public String mpin;
}

