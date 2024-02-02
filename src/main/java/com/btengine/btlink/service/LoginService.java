package com.btengine.btlink.service;

import com.btengine.btlink.model.Login;
import com.btengine.btlink.repository.LoginRepository;
import jakarta.persistence.Column;
import jakarta.transaction.Transactional;
import org.apache.hc.client5.http.auth.InvalidCredentialsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.validation.BindValidationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.*;

@Service
public class LoginService {

    @Autowired
    LoginRepository loginRepository;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public LoginService(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    public List<Login> getAllLogins() {
        return loginRepository.findAll();
    }

    public Login authenticateLogin(String userId, String mpin) throws InvalidCredentialsException {
        if (userId.isEmpty() || mpin.isEmpty()) {
            throw new InvalidCredentialsException("User ID or MPIN is empty");
        }

        Optional<Login> login = loginRepository.findUserByCredentials(userId, mpin);

        if (login.isPresent()) {
            return login.get();
        } else {
            throw new InvalidCredentialsException("User ID or MPIN is false");
        }
    }



    public Login authenticateLoginWithHash(String userId, String mpin) throws InvalidCredentialsException {
        if (userId.isEmpty() || mpin.isEmpty()) {
            throw new InvalidCredentialsException("User ID or MPIN is empty");
        }

        Optional<Login> login = loginRepository.findUserByUserId(userId);

        if (login.isPresent()) {
            if (passwordEncoder.matches(mpin, login.get().getMpin())) {
                return login.get();
            } else {
                throw new InvalidCredentialsException("User ID or MPIN is incorrect");
            }
        } else {
            throw new InvalidCredentialsException("User ID or MPIN is false");
        }
    }

    public Login authenticateTransactionPassword(String userId, String transactionPassword) throws InvalidCredentialsException{
        if (userId.isEmpty() || transactionPassword.isEmpty()) {
            throw new InvalidCredentialsException("User ID or Transaction Password is empty");
        }

        Optional<Login> login = loginRepository.findUserByTransactionPassword(userId, transactionPassword);

        if (login.isPresent()) {
            return login.get();
        } else {
            throw new InvalidCredentialsException("User ID or Transaction Password is false");
        }
    }

    public Login authenticateTransactionPasswordWithHash(String userId, String transactionPassword) throws InvalidCredentialsException{
        if(userId.isEmpty() || transactionPassword.isEmpty()){
            throw new InvalidCredentialsException("User ID or Transaction Passwrod is empty");
        }
        Optional<Login> login = loginRepository.findUserByUserId(userId);
        if(login.isPresent()){
            if(passwordEncoder.matches(transactionPassword, login.get().transactionPassword)){
                return login.get();
            }else{
                throw new InvalidCredentialsException("User ID or Transaction Password is incorrect");
            }
        }else{
            throw new InvalidCredentialsException("User ID or Transaction Password is false");
        }
    }

    @Transactional
    public void registerLogin(String userId, String transactionPassword, String mpin) {
        if (userId.isEmpty() || transactionPassword.isEmpty() || mpin.isEmpty()) {
            throw new IllegalArgumentException("User ID or Transaction Password or MPIN is empty");
        }

        if (mpin.length() != 6) {
            throw new IllegalArgumentException("MPIN must be 6 characters long");
        }

        if (!validatePasswordStrength(transactionPassword)) {
            throw new IllegalArgumentException("Transaction password must have at least 8 characters, " +
                    "including uppercase, lowercase, a number, and a special character");
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hashedMpin = encoder.encode(mpin);
        String hashedTransactionPassword = encoder.encode(transactionPassword);

        Login login = new Login();

        login.setSkLogin(UUID.randomUUID());

        Random random = new Random();
        int randomNumber = random.nextInt(90000000) + 10000000; // Range from 10000000 to 99999999
        String accountNumber = String.valueOf(randomNumber);
        login.setAccountNumber(accountNumber);

        login.setUserId(userId);
        login.setMpin(hashedMpin);
        login.setTransactionPassword(hashedTransactionPassword);

        loginRepository.save(login);
    }

    private boolean validatePasswordStrength(String password) {
        return password.length() >= 8 &&
                password.matches(".*[A-Z].*") &&
                password.matches(".*[a-z].*") &&
                password.matches(".*\\d.*") &&
                password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\|,.<>/?].*");
    }
}
