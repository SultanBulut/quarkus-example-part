package com.avsos.repository;

import com.avsos.entity.FlightLeg;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class FlightRepository implements PanacheRepository<FlightLeg> {
    public Optional<FlightLeg> findByFlightNumber(String flightNumber){
        return find(" FROM FlightLeg where flightNumber=?1 ",flightNumber).firstResultOptional();
    }
}
