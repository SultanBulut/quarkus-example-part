
package org.acme;
import org.apache.camel.ProducerTemplate;
import jakarta.inject.Inject;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MovieProducer {

    @Inject
    ProducerTemplate producerTemplate;

    public void sendMovieToKafka(MovieDTO movieDTO) {
        producerTemplate.sendBody("kafka:movies-out", movieDTO);
    }
}
