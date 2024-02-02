package com.btengine.btlink.config;

import com.btengine.btlink.model.Login;
import com.btengine.btlink.repository.LoginRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    LoginRepository loginRepository;

    @Override
    public UserDetails loadUserByUsername(String accountNumber) throws UsernameNotFoundException {
        Optional<Login> login = loginRepository.findUserByAccountNumber(accountNumber);

        if (login.isPresent()) {
            return new User(
                    login.get().getUserId(),
                    login.get().getMpin(),
                    Collections.emptyList()
            );
        } else {
            return null;
        }
    }
}
