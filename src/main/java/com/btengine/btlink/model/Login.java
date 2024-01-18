package com.btengine.btlink.model;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.*;

//@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "login",schema = "bt-link")
public class Login {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String skLogin;

    private String accountNumber;
    private String userId;
    private String transactionPassword;
    private String mpin;
}

