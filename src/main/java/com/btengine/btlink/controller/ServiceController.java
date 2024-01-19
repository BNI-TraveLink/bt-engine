package com.btengine.btlink.controller;

import com.btengine.btlink.model.Service;
import com.btengine.btlink.model.Stations;
import com.btengine.btlink.service.LinkService;
//import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

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

    @GetMapping("/{sk_service}")
    public Service getServiceBySkService(@PathVariable("sk_service") UUID skService) {
        return linkService.getServiceBySkService(skService)
                .orElseThrow(() -> new RuntimeException("Service tidak ditemukan untuk sk_service: " + skService));
    }
}
