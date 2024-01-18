package com.btengine.btlink.controller;


import com.btengine.btlink.model.Login;
import com.btengine.btlink.service.LoginService;
//import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/logins")
public class LoginController {

    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping
//    @ApiOperation(value = "get all customer")
    public List<Login> getAllLogins() {
        return loginService.getAllLogins();
    }
}
