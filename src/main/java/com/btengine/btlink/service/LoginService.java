package com.btengine.btlink.service;

import com.btengine.btlink.model.Login;
import com.btengine.btlink.repository.LoginRepository;
import org.apache.hc.client5.http.auth.InvalidCredentialsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    LoginRepository loginRepository;

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
}
