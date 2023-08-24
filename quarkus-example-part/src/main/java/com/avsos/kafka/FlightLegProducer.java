package com.avsos.kafka;


import com.avsos.dto.FlightLegDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.ProducerTemplate;

@ApplicationScoped
public class FlightLegProducer {
    @Inject
    ProducerTemplate producerTemplate;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public void sendFlightLegToKafka(FlightLegDTO flightLegDTO) {
        try {
            String flightLegJson = objectMapper.writeValueAsString(flightLegDTO);
            producerTemplate.sendBody("kafka:flightlegs-out", flightLegJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
/*import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import jakarta.inject.Inject;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FlightLegProducer {

    @Inject
    @Channel("flightlegs-out")
    Emitter<FlightLegDTO> emitter;

    public void sendFlightLegToKafka(FlightLegDTO flightLegDTO) {
        emitter.send(flightLegDTO);
    }
}*/
