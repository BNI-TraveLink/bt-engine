package com.btengine.btlink.controller;

//import com.btengine.btlink.model.Service;
//import com.btengine.btlink.service.LinkService;
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
//@RequestMapping("/service")
//
//public class ServiceController {
//
//    private final LinkService linkService;
//
//    @Autowired
//    private ServiceController (LinkService linkService) { this.linkService = linkService; }; // Nama service harus sesuai
//
//    @GetMapping
//    public List<Service> getAllService() { // Nama class harus sesuai
//        return linkService.getAllService(); // Panggil metode getAllStation() pada repository
//    }
//
//    @GetMapping("/{sk_service}")
//    public Service getServiceBySkService(@PathVariable("sk_service") UUID skService) {
//        return linkService.getServiceBySkService(skService)
//                .orElseThrow(() -> new RuntimeException("Service tidak ditemukan untuk sk_service: " + skService));
//    }
//
//}



import com.btengine.btlink.model.FacilityService;
import com.btengine.btlink.service.FacilitySvcService;
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
@RequestMapping("/service")
public class FacilityServiceController {

    @Autowired
    private FacilitySvcService linkService;

    @GetMapping("/{sk_service}")
    public ResponseEntity<Optional<FacilityService>> getServiceBySkService(@PathVariable UUID sk_service) {
        Optional<FacilityService> service = linkService.getServiceBySkService(sk_service);
        if (ObjectUtils.isEmpty(service)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(null); // atau throw NotFoundException atau sejenisnya
        }
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(service);
    }

    @GetMapping
    public ResponseEntity<List<FacilityService>> getAllServices() {
        List<FacilityService> allServices = linkService.getAllService();
        if (allServices.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(null); // atau throw NotFoundException atau sejenisnya
        }
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(allServices);
    }

    // Metode POST, DELETE, dll. dapat ditambahkan sesuai kebutuhan proyek
}
