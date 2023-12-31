package com.avsos.service;

import com.avsos.dto.FlightLegDTO;
import com.avsos.dto.SimplifiedFlightLegDTO;
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
        FlightLeg flightLeg= new FlightLeg(flightLegDTO.getFlightLegId(),flightLegDTO.getFlightNumber(), flightLegDTO.getDepartureAirport(),flightLegDTO.getArrivalAirport(),flightLegDTO.getDepartureGate(),flightLegDTO.getArrivalGate(),flightLegDTO.getDepartureDate());
        flightLeg.assignFlightCrews(findFlightCrews(flightLegDTO.getCrewIds()));
        //flightLeg.assignAirCrafts(findAirCrafts(flightLegDTO.getAircraftIds()));
        flightLeg.assignAirCraft(findAirCraft(flightLegDTO.getAircraftId()));
        flightRepository.persist(flightLeg);
        return flightRepository.isPersistent(flightLeg);
    }


    public List<FlightCrew> findFlightCrews (List<Long> flightCrewIds){
        List<FlightCrew> flightCrews =new ArrayList<>();
        for(long id: flightCrewIds){
            flightCrews.add(flightCrewRepository.findById(id));
        }
        return flightCrews;
    }

    public AirCraft findAirCraft(Long aircraftId) {
        return airCraftRepository.findById(aircraftId);
    }

   /* public List<AirCraft> findAirCrafts(List<Long> aircraftIds){
        List<AirCraft> airCrafts= new ArrayList<>();
        for(long id:aircraftIds){
            airCrafts.add(airCraftRepository.findById(id));
        }
        return airCrafts;
    }*/
    public List<FlightLeg> getAllFlightLeg(){ return flightRepository.findAll().list();}

    @Transactional
    public FlightLeg getFlightLegByFlightLegID(String flightLegID){
        return FlightLeg.findFlightLegByFlightLegID(flightLegID);
    }


    @Transactional
    public void updateFlightLeg(String flightLegId,FlightLegDTO flightLegDTO){
        FlightLeg flightLeg1 = FlightLeg.findFlightLegByFlightLegID(flightLegId);
        flightLeg1.setFlightNumber(flightLegDTO.getFlightNumber());
        flightLeg1.setArrivalAirport(flightLegDTO.getArrivalAirport());
        flightLeg1.setDepartureAirport(flightLegDTO.getDepartureAirport());
        flightLeg1.setArrivalGate(flightLegDTO.getArrivalGate());
        flightLeg1.setDepartureGate(flightLegDTO.getDepartureGate());
        flightLeg1.setDepartureDate(flightLegDTO.getDepartureDate());

    }

    @Transactional
    public void deleteFlightLeg(String flightLegId) {
        FlightLeg flightLeg = FlightLeg.findFlightLegByFlightLegID(flightLegId);
        if (flightLeg != null) {
            flightLeg.delete();
        }
    }

    // Overloaded method to convert a single FlightLeg to SimplifiedFlightLegDTO
    public SimplifiedFlightLegDTO convertToSimplifiedDTO(FlightLeg flightLeg) {
        SimplifiedFlightLegDTO simplifiedDTO = new SimplifiedFlightLegDTO();
        simplifiedDTO.setFlightLegId(flightLeg.getFlightLegId());
        simplifiedDTO.setFlightNumber(flightLeg.getFlightNumber());
        simplifiedDTO.setDepartureAirport(flightLeg.getDepartureAirport());
        simplifiedDTO.setArrivalAirport(flightLeg.getArrivalAirport());
        simplifiedDTO.setDepartureGate(flightLeg.getDepartureGate());
        simplifiedDTO.setArrivalGate(flightLeg.getArrivalGate());
        simplifiedDTO.setDepartureDate(flightLeg.getDepartureDate());
        return simplifiedDTO;
    }

    // Overloaded method to convert a list of FlightLegs to SimplifiedFlightLegDTOs
    public List<SimplifiedFlightLegDTO> convertToSimplifiedDTOs(List<FlightLeg> flightLegs) {
        List<SimplifiedFlightLegDTO> simplifiedFlightLegs = new ArrayList<>();
        for (FlightLeg flightLeg : flightLegs) {
            simplifiedFlightLegs.add(convertToSimplifiedDTO(flightLeg));
        }
        return simplifiedFlightLegs;
    }


}
