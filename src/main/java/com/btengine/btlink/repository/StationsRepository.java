package com.btengine.btlink.repository;

import com.btengine.btlink.model.Stations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

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
}
