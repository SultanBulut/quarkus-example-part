
package org.acme;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.ProducerTemplate;
import jakarta.inject.Inject;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MovieProducer {

    @Inject
    ProducerTemplate producerTemplate;

   /* public void sendMovieToKafka(MovieDTO movieDTO) {
        producerTemplate.sendBody("kafka:movies-out", movieDTO);
    }*/

    private final ObjectMapper objectMapper = new ObjectMapper();

    public void sendMovieToKafka(MovieDTO movieDTO) {
        try {
            String movieJson = objectMapper.writeValueAsString(movieDTO);
            producerTemplate.sendBody("kafka:movies-out", movieJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
/*package org.acme;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import jakarta.inject.Inject;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MovieProducer {

    @Inject
    @Channel("movies-out")
    Emitter<MovieDTO> emitter;

    public void sendMovieToKafka(MovieDTO movieDTO) {
        emitter.send(movieDTO);
    }
}*/