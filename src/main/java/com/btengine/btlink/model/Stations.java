package com.btengine.btlink.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "stations",schema = "bt-link")
public class Stations {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="sk_Stations")
    public UUID skStation;

    @Column(name = "station_name", nullable = false)
    private String station_name;

    @ManyToOne
    @JoinColumn(name = "fk_service", nullable = false)
    private FacilityService fkService;
}




//package com.btengine.btlink.model;
//
//import jakarta.persistence.*;
//import lombok.*;
//import org.hibernate.annotations.GenericGenerator;
//
//import java.util.UUID;
//
//@Entity
//@Getter
//@Setter
//@NoArgsConstructor
//@Table(name = "stations", schema = "bt-link")
//public class Stations {
//
//    @Id
//    @GeneratedValue(generator = "uuid2")
//    @GenericGenerator(name = "uuid2", strategy = "uuid2")
//    @Column(name = "sk_station")
//    private UUID skStation;
//
//    @Column(name = "station_name", nullable = false)
//    private String stationName;
//
//    @ManyToOne
//    @JoinColumn(name = "fk_service", nullable = false)
//    private Service fkService;
//}

