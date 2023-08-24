/*package com.avsos.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.smallrye.reactive.messaging.kafka.Record;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.jboss.logging.Logger;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FlightLegConsumer {

    @Inject
    ObjectMapper objectMapper;
    @Inject
    FlightRepository flightRepository;

    private final Logger logger = Logger.getLogger(FlightLegConsumer.class);

    @Incoming("flightlegs-in")
    @Transactional
    public void receive(Record<Integer, String> record) {
        logger.infof("Got a flight leg: %d - %s", record.key(), record.value());
        try {
            FlightLegDTO flightLegDTO = objectMapper.readValue(record.value(), FlightLegDTO.class);


            FlightLeg flightLeg = new FlightLeg(
                    flightLegDTO.getFlightNumber(),
                    flightLegDTO.getDepartureAirport(),
                    flightLegDTO.getArrivalAirport(),
                    flightLegDTO.getDepartureGate(),
                    flightLegDTO.getArrivalGate(),
                    flightLegDTO.getDepartureDate()
            );

            flightRepository.persist(flightLeg);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}*/