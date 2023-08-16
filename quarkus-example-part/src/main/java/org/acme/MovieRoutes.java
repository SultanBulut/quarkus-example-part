
package org.acme;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.apache.camel.builder.RouteBuilder;
import jakarta.enterprise.context.ApplicationScoped;
import org.apache.camel.model.dataformat.JsonLibrary;

import java.util.Optional;

@ApplicationScoped
public class MovieRoutes extends RouteBuilder {

    @Inject
    MovieRepository movieRepository;
    @Override
    public void configure() throws Exception {
        from("kafka:movies-in")
                .log("received: ${body}")
                .unmarshal().json(JsonLibrary.Jackson,MovieDTO.class)
                .bean(this, "processMovie")
                .marshal().json()
                .log("Sent: ${body}")
                .to("kafka:movies-out");
    }

    @Transactional
    public Movie processMovie(MovieDTO movieDTO) {
        Optional<Movie> optionalMovie = movieRepository.findByTitle(movieDTO.getTitle());
        if (optionalMovie.isEmpty()) {
            Movie movie = new Movie(movieDTO.getTitle(), movieDTO.getReleaseYear(),movieDTO.getMovieDirection(),movieDTO.getMovieCast());
            movieRepository.persist(movie);
            //movie.setReleaseYear(1230);
            return movie;

        } else {
            Movie existingMovie = optionalMovie.get();
            existingMovie.setReleaseYear(movieDTO.getReleaseYear());
            //movieRepository.persist(existingMovie);
            return existingMovie;
        }
    }


   /* @Transactional
    public void processMovie(MovieDTO movieDTO) {
        Optional<Movie> optionalMovie = movieRepository.findByTitleAndDirector(movieDTO.getTitle(),movieDTO.getDirector());
        if(optionalMovie.isEmpty()) {
            Movie movie = new Movie(movieDTO.getTitle(), movieDTO.getDirector(), movieDTO.getReleaseYear());
            movieRepository.persist(movie);
            movie.setReleaseYear(1230);
        }
       else{
            Movie existingMovie = optionalMovie.get();
            existingMovie.setReleaseYear(movieDTO.getReleaseYear());
            movieRepository.persist(existingMovie);
            
        }
    }*/
}
/*package org.acme;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.smallrye.reactive.messaging.kafka.Record;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.jboss.logging.Logger;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MovieConsumer {

    @Inject
    ObjectMapper objectMapper;
    @Inject
    MovieRepository movieRepository;

    private final Logger logger = Logger.getLogger(MovieConsumer.class);

    @Incoming("movies-in")
    @Transactional
    public void receive(Record<Integer, String> record) {
        logger.infof("Got a movie: %d - %s", record.key(), record.value());
        try {
            MovieDTO movieDTO = objectMapper.readValue(record.value(), MovieDTO.class);
            Movie movie = new Movie(movieDTO.getTitle(), movieDTO.getDirector(), movieDTO.getReleaseYear());
            movieRepository.persist(movie);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}*/