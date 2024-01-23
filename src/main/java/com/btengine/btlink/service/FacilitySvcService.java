package com.btengine.btlink.service;

import com.btengine.btlink.model.FacilityService;
import com.btengine.btlink.repository.FacilityServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@org.springframework.stereotype.Service
public class FacilitySvcService {

    @Autowired
    FacilityServiceRepository facilityServiceRepository;

    @Autowired
    public FacilitySvcService(FacilityServiceRepository facilityServiceRepository) {this.facilityServiceRepository = facilityServiceRepository;}

    public List<FacilityService> getAllService() {
        return facilityServiceRepository.findAll();
    }

    public Optional<FacilityService> getServiceBySkService(UUID skService) {
        return facilityServiceRepository.findBySkService(skService);
    }
}
