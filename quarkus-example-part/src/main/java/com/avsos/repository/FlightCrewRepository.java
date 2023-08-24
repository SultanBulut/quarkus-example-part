package com.avsos.repository;

import com.avsos.entity.FlightCrew;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FlightCrewRepository implements PanacheRepository<FlightCrew> {
}
