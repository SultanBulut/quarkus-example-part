package com.avsos.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class AirCraft {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "aircraft_id")
    private Long aircraftID;

    @Column(name = "ac_type")
    private String acType;

    private String subtype;

    @Column(name = "customized_type")
    private String customizedType;

    @Column(name = "name_of_aircraft")
    private String nameOfAircraft;

    @Column(name = "number_of_seats")
    private Long numberOfSeats;

    private String registrar;
    private String owner;
    private String contract;

    @JsonIgnore
    @ManyToMany(mappedBy = "aircrafts")
    private List<FlightLeg> flightLegs  = new ArrayList<>();


    public AirCraft(String acType,String subtype,String customizedType,String nameOfAircraft,Long numberOfSeats,String registrar,String owner,String contract){
        this.acType=acType;
        this.subtype=subtype;
        this.customizedType=customizedType;
        this.nameOfAircraft=nameOfAircraft;
        this.numberOfSeats=numberOfSeats;
        this.registrar=registrar;
        this.owner=owner;
        this.contract=contract;
    }


}
