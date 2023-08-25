package com.avsos.service;

import com.avsos.dto.FlightLegDTO;
import com.avsos.entity.AirCraft;
import com.avsos.entity.FlightCrew;
import com.avsos.entity.FlightLeg;
import com.avsos.kafka.FlightLegProducer;
import com.avsos.repository.AirCraftRepository;
import com.avsos.repository.FlightCrewRepository;
import com.avsos.repository.FlightRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class FlightLegService {
    @Inject
    FlightRepository flightRepository;
    @Inject
    FlightLegProducer flightLegProducer;
    @Inject
    FlightCrewRepository flightCrewRepository;

    @Inject
    AirCraftRepository airCraftRepository;
    @Transactional
    public boolean createFlightLeg (FlightLegDTO flightLegDTO){
        flightLegProducer.sendFlightLegToKafka(flightLegDTO);
        return helper(flightLegDTO);
    }

    public boolean helper(FlightLegDTO flightLegDTO){
        FlightLeg flightLeg= new FlightLeg(flightLegDTO.getFlightNumber(), flightLegDTO.getDepartureAirport(),flightLegDTO.getArrivalAirport(),flightLegDTO.getDepartureGate(),flightLegDTO.getArrivalGate(),flightLegDTO.getDepartureDate());
        flightLeg.assignFlightCrews(findFlightCrews(flightLegDTO.getCrewIds()));
        flightLeg.assignAirCrafts(findAirCrafts(flightLegDTO.getAircraftIds()));
        flightRepository.persist(flightLeg);
        return flightLeg.getLegId() != null;

        //return flightRepository.isPersistent(flightLeg);
    }


    public List<FlightCrew> findFlightCrews (List<Long> flightCrewIds){
        List<FlightCrew> flightCrews =new ArrayList<>();
        for(long id: flightCrewIds){
            flightCrews.add(flightCrewRepository.findById(id));
        }
        return flightCrews;
    }

    public List<AirCraft> findAirCrafts(List<Long> aircraftIds){
        List<AirCraft> airCrafts= new ArrayList<>();
        for(long id:aircraftIds){
            airCrafts.add(airCraftRepository.findById(id));
        }
        return airCrafts;
    }
    public List<FlightLeg> getAllFlightLeg(){ return flightRepository.findAll().list();}

    @Transactional
    public FlightLeg getFlightLegByFlightNumber(String flightNumber){
        return FlightLeg.findFlightLegByFlightNumber(flightNumber);
    }

    @Transactional
    public void updateFlightLeg(String flightNumber,FlightLegDTO flightLegDTO){
        FlightLeg flightLeg1 = FlightLeg.findFlightLegByFlightNumber(flightNumber);
        flightLeg1.setArrivalAirport(flightLegDTO.getArrivalAirport());
        flightLeg1.setDepartureAirport(flightLegDTO.getDepartureAirport());
        flightLeg1.setArrivalGate(flightLegDTO.getArrivalGate());
        flightLeg1.setDepartureGate(flightLegDTO.getDepartureGate());
        flightLeg1.setDepartureDate(flightLegDTO.getDepartureDate());

    }

    @Transactional
    public void deleteFlightLeg(String flightNumber) {
        FlightLeg flightLeg = FlightLeg.findFlightLegByFlightNumber(flightNumber);
        if (flightLeg != null) {
            flightLeg.delete();
        }
    }
}
