package com.btengine.btlink.service;

import com.btengine.btlink.model.Login;
import com.btengine.btlink.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
