package com.btengine.btlink.repository;

import com.btengine.btlink.model.FacilityService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface FacilityServiceRepository extends JpaRepository<FacilityService, UUID> {
    List<FacilityService> findAll();
    Optional<FacilityService> findBySkService(UUID skService);

    @Query(value = "SELECT * FROM \"bt-link\".Service s WHERE s.name = :name", nativeQuery = true)
    FacilityService findServicebyServiceName(@Param("name") String name);


}

