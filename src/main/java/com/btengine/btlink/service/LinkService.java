package com.btengine.btlink.service;

import com.btengine.btlink.model.Service;
import com.btengine.btlink.model.Stations;
import com.btengine.btlink.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@org.springframework.stereotype.Service
public class LinkService {

    @Autowired
    ServiceRepository serviceRepository;

    @Autowired
    public LinkService(ServiceRepository serviceRepository) {this.serviceRepository = serviceRepository;}

    public List<Service> getAllService() {
        return serviceRepository.findAll();
    }

    public Optional<Service> getServiceBySkService(UUID skService) {
        return serviceRepository.findBySkService(skService);
    }
}
