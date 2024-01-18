package com.btengine.btlink.service;

import com.btengine.btlink.model.Stations;
import com.btengine.btlink.repository.StationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StationsService {

    @Autowired
    StationsRepository stationsRepository;

    @Autowired
    public StationsService (StationsRepository stationsRepository) {this.stationsRepository = stationsRepository;}

    public List<Stations> getAllStations() {
        return stationsRepository.findAll();
    }
}
