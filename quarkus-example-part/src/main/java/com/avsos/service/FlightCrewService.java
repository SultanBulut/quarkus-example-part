package com.avsos.service;

import com.avsos.dto.FlightCrewDTO;
import com.avsos.entity.FlightCrew;
import com.avsos.repository.FlightCrewRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class FlightCrewService {
    @Inject
    FlightCrewRepository flightCrewRepository;

    @Transactional
    public boolean createFlightCrew(FlightCrewDTO flightCrewDTO){
        FlightCrew flightCrew= new FlightCrew(flightCrewDTO.getCrewName(),flightCrewDTO.getCrewPosition(),flightCrewDTO.getState(), flightCrewDTO.getDutyType());
        flightCrewRepository.persist(flightCrew);
        return flightCrewRepository.isPersistent(flightCrew);
    }

    public List<FlightCrew> getAllFlightCrew(){
        return flightCrewRepository.findAll().list();
    }
}
