package com.btengine.btlink.controller;


//import com.btengine.btlink.model.Stations;
//import com.btengine.btlink.service.StationsService;
////import io.swagger.annotations.ApiOperation;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//import java.util.UUID;
//
//@RestController
//@RequestMapping("/stations")
//public class StationsController {
//
//    private final StationsService stationsService;
//
//    @Autowired
//    private StationsController (StationsService stationsService)
//    { this.stationsService = stationsService; }; // Nama service harus sesuai
//
//    @GetMapping
//    public List<Stations> getAllStations() { // Nama class harus sesuai
//        return stationsService.getAllStations(); // Panggil metode getAllStation() pada repository
//    }
//
//    // Mendapatkan data berdasarkan sk_station
//    @GetMapping("/{sk_stations}")
//    public Stations getStationBySkStation(@PathVariable("sk_stations") UUID skStation) {
//        return stationsService.getStationBySkStation(skStation)
//                .orElseThrow(() -> new RuntimeException("Stasiun tidak ditemukan untuk sk_station: " + skStation));
//    }
//
//}



import com.btengine.btlink.model.Stations;
import com.btengine.btlink.service.StationsService;
//import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin("*")
@RestController
@RequestMapping("/stations")
public class StationsController {

    @Autowired
    private StationsService stationsService;

    @GetMapping("/{sk_station}")
    public ResponseEntity<Optional<Stations>> getStationBySkStation(@PathVariable UUID sk_station) {
        Optional<Stations> station = stationsService.getStationBySkStation(sk_station);
        if (ObjectUtils.isEmpty(station)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(null); // atau throw NotFoundException atau sejenisnya
        }
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(station);
    }

    @GetMapping
    public ResponseEntity<List<Stations>> getAllStations() {
        List<Stations> allStations = stationsService.getAllStations();
        if (allStations.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(null); // atau throw NotFoundException atau sejenisnya
        }
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(allStations);
    }



    // Metode POST, DELETE, dll. dapat ditambahkan sesuai kebutuhan proyek
}
