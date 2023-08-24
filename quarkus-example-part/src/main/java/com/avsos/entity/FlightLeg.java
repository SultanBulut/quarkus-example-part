package com.avsos.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class FlightLeg extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "leg_id")
    private Long legId;

    @Column(name = "flight_number")
    private String flightNumber;

    @Column(name = "departure_airport")
    private String departureAirport;

    @Column(name = "arrival_airport")
    private String arrivalAirport;

    @Column(name = "departure_gate")
    private String departureGate;

    @Column(name = "arrival_gate")
    private String arrivalGate;

    @Column(name = "departure_date")
    private Date departureDate;

    @ManyToMany
    @JoinTable(name="flight_leg_flight_crew",joinColumns =@JoinColumn(name = "leg_id"),
            inverseJoinColumns = @JoinColumn(name = "crew_id"))
    private List<FlightCrew> flightCrews =new ArrayList<>();

    @ManyToMany
    @JoinTable(name="flight_leg_aircraft",joinColumns =@JoinColumn(name = "leg_id"),
            inverseJoinColumns = @JoinColumn(name = "aircraft_id"))
    private List<AirCraft> aircrafts  =new ArrayList<>();


    public FlightLeg(String flightNumber, String departureAirport, String arrivalAirport, String departureGate, String arrivalGate, Date departureDate) {
        this.flightNumber=flightNumber;
        this.departureAirport=departureAirport;
        this.arrivalAirport=arrivalAirport;
        this.departureGate=departureGate;
        this.arrivalGate=arrivalGate;
        this.departureDate=departureDate;
    }

    public static FlightLeg findFlightLegByFlightNumber(String flightNumber) {
        return find("flightNumber", flightNumber).firstResult();
    }

    public void assignFlightCrews(List<FlightCrew> flightCrews) {
        this.flightCrews.addAll(flightCrews);
    }

    public void assignAirCrafts(List<AirCraft> aircrafts) {
        this.aircrafts.addAll(aircrafts);
    }


}
