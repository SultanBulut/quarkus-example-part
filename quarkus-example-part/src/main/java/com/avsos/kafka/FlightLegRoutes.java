package com.avsos.kafka;

import com.avsos.dto.FlightLegDTO;
import com.avsos.entity.FlightLeg;
import com.avsos.repository.FlightRepository;
import com.avsos.service.FlightLegService;
import jakarta.inject.Inject;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;

import java.util.Optional;

@ApplicationScoped
public class FlightLegRoutes extends RouteBuilder {

    @Inject
    FlightRepository flightRepository;
    @Inject
    FlightLegService flightLegService;

    @Override
    public void configure() throws Exception {
        from("kafka:flightlegs-in")
                .log("Received: ${body}")
                .unmarshal().json(JsonLibrary.Jackson, FlightLegDTO.class)
                .bean(this, "processFlightLeg")
                .marshal().json()
                .log("Sent: ${body}")
                .to("kafka:flightlegs-out");
    }

    @Transactional
    public FlightLeg processFlightLeg(FlightLegDTO flightLegDTO) {
        Optional<FlightLeg> optionalFlightLeg = flightRepository.findByFlightNumber(flightLegDTO.getFlightNumber());

        if (optionalFlightLeg.isEmpty()) {
            FlightLeg flightLeg = new FlightLeg(
                    flightLegDTO.getFlightNumber(),
                    flightLegDTO.getDepartureAirport(),
                    flightLegDTO.getArrivalAirport(),
                    flightLegDTO.getDepartureGate(),
                    flightLegDTO.getArrivalGate(),
                    flightLegDTO.getDepartureDate()
            );
            flightLeg.assignFlightCrews(flightLegService.findFlightCrews(flightLegDTO.getCrewIds()));
            flightLeg.assignAirCrafts(flightLegService.findAirCrafts(flightLegDTO.getAircraftIds()));
            flightRepository.persist(flightLeg);

            return flightLeg;
        } else {
            FlightLeg existingFlightLeg = optionalFlightLeg.get();
            existingFlightLeg.setDepartureAirport(flightLegDTO.getDepartureAirport());
            existingFlightLeg.setArrivalAirport(flightLegDTO.getArrivalAirport());
            existingFlightLeg.setDepartureGate(flightLegDTO.getDepartureGate());
            existingFlightLeg.setArrivalGate(flightLegDTO.getArrivalGate());
            existingFlightLeg.setDepartureDate(flightLegDTO.getDepartureDate());

            return existingFlightLeg;
        }
    }

}
