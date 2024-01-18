package com.btengine.btlink.controller;


import com.btengine.btlink.model.Stations;
import com.btengine.btlink.service.StationsService;
//import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/stations")
public class StationsController {

    private final StationsService stationsService;

    @Autowired
    private StationsController (StationsService stationsService) { this.stationsService = stationsService; }; // Nama service harus sesuai

    @GetMapping
    public List<Stations> getAllStations() { // Nama class harus sesuai
        return stationsService.getAllStations(); // Panggil metode getAllStation() pada repository
    }
}