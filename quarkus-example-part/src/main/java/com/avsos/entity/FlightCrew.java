package com.avsos.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
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
public class FlightCrew extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "crew_id")
    private Long crewId;

    @Column(name = "crew_name")
    private String crewName;

    @Column(name = "crew_position")
    private String crewPosition;

    private String state;

    @Column(name = "duty_type")
    private String dutyType;

    @JsonIgnore
    @ManyToMany(mappedBy = "flightCrews")
    private List<FlightLeg> flightLegs = new ArrayList<>();

    public FlightCrew(String crewName,String crewPosition,String state,String dutyType){
        this.crewName=crewName;
        this.crewPosition=crewPosition;
        this.state=state;
        this.dutyType=dutyType;
    }


}
