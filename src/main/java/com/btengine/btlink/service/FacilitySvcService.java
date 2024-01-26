package com.btengine.btlink.service;

import com.btengine.btlink.model.FacilityService;
import com.btengine.btlink.model.Stations;
import com.btengine.btlink.repository.FacilityServiceRepository;
import com.btengine.btlink.repository.StationsRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@org.springframework.stereotype.Service
public class FacilitySvcService {

    @Autowired
    FacilityServiceRepository facilityServiceRepository;

    @Autowired
    StationsRepository stationsRepository;
    
    @Autowired
    public FacilitySvcService(FacilityServiceRepository facilityServiceRepository) {this.facilityServiceRepository = facilityServiceRepository;}

    public List<FacilityService> getAllService() {
        return facilityServiceRepository.findAll();
    }

    public Optional<FacilityService> getServiceBySkService(UUID skService) {
        return facilityServiceRepository.findBySkService(skService);
    }

    public List<Stations> getStationsByServiceName(String name) {
        return stationsRepository.getStationsByServiceName(name);
    }

    public FacilityService getServiceByName(String name){
        return facilityServiceRepository.findServicebyServiceName(name);
    }
}
