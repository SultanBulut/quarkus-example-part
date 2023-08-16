
package com.avsos.kafka;
import com.avsos.dto.MovieDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.ProducerTemplate;
import jakarta.inject.Inject;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MovieProducer {

    @Inject
    ProducerTemplate producerTemplate;

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