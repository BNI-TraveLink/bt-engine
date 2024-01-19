package com.btengine.btlink.controller;


import com.btengine.btlink.model.Login;
import com.btengine.btlink.service.LoginService;
//import io.swagger.annotations.ApiOperation;
import org.apache.hc.client5.http.auth.InvalidCredentialsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/logins")
public class LoginController {
    @Autowired
    LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping
//    @ApiOperation(value = "get all customer")
    public List<Login> getAllLogins() {
        return loginService.getAllLogins();
    }

    @PostMapping("/user")
    public ResponseEntity<?> authenticateLogin(@RequestParam String userId, @RequestParam String mpin) {
        try {
            Login login = loginService.authenticateLogin(userId, mpin);

            return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(login);
        } catch (InvalidCredentialsException e) {
            throw new RuntimeException(e);
        }
    }
}
