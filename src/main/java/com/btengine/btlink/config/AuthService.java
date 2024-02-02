package com.btengine.btlink.config;

import com.btengine.btlink.model.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthService {
    private AuthenticationManager authenticationManager;

    private JwtTokenProvider jwtTokenProvider;

//    @Override
    public String login(Login login){
        try{
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    login.getUserId(),
                    login.getMpin()
            ));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String token = jwtTokenProvider.generateJwtToken(login.getAccountNumber(), login.getUserId());

            return token;
        } catch(Exception e){
            return null;
        }


    }
}
