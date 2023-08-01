package org.acme;

import io.smallrye.reactive.messaging.kafka.Record;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.jboss.logging.Logger;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MovieConsumer {

    private final Logger logger = Logger.getLogger(MovieConsumer.class);

    @Incoming("movies-in")
    public void receive(Record<Integer, String> record) {
        logger.infof("Got a movie: %d - %s", record.key(), record.value());
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