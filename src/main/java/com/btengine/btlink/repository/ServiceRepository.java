package com.btengine.btlink.repository;

import com.btengine.btlink.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ServiceRepository extends JpaRepository<Service, UUID> {
    List<Service> findAll();
    Optional<Service> findBySkService(UUID skService);
}

