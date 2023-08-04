package org.acme;

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
}

/*package org.acme;

import io.smallrye.reactive.messaging.kafka.Record;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.jboss.logging.Logger;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MovieConsumer {

    private final Logger logger = Logger.getLogger(MovieConsumer.class);

    @Incoming("movies-in")
    public void receive(Movie movie) {
        logger.infof("Got a movie: %d - %s (Directed by: %s)", movie.getReleaseYear(), movie.getTitle(), movie.getDirector() );
    }
}

/*package org.acme;

import io.smallrye.reactive.messaging.kafka.Record;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.jboss.logging.Logger;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MovieConsumer {

    private final Logger logger = Logger.getLogger(MovieConsumer.class);

    @Incoming("movies-in")
    public void receive( String record) {
        logger.infof("Got a movie:  - %s", record);
    }
}
/*package org.acme;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MovieConsumer {
    @Incoming("movies-in")
    public Movie receive(Movie movie) {
        System.out.println("Received post: " + movie.toString());

        return movie;
    }
}*/