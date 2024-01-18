package com.btengine.btlink.controller;

import com.btengine.btlink.model.Service;
import com.btengine.btlink.service.LinkService;
//import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/service")

public class ServiceController {

    private final LinkService linkService;

    @Autowired
    private ServiceController (LinkService linkService) { this.linkService = linkService; }; // Nama service harus sesuai

    @GetMapping
    public List<Service> getAllService() { // Nama class harus sesuai
        return linkService.getAllService(); // Panggil metode getAllStation() pada repository
    }

}
