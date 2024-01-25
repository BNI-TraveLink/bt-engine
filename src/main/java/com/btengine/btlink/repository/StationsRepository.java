package com.btengine.btlink.repository;

import com.btengine.btlink.model.Stations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

//@Repository
//public interface StationsRepository extends JpaRepository<Stations, String> {
//}

@Repository
public interface StationsRepository extends JpaRepository<Stations, UUID> {
    List<Stations> findAll();
    Optional<Stations> findBySkStation(UUID skStation);

    @Query
    (value = "SELECT * FROM \"bt-link\".stations s INNER JOIN \"bt-link\".service t ON s.fk_service = t.sk_service WHERE t.name = :serviceName", nativeQuery = true)
    List<Stations> getStationsByServiceName(@Param("serviceName") String serviceName);

}
