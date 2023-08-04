package org.acme;

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
}

/*package org.acme;

import io.smallrye.reactive.messaging.kafka.Record;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import jakarta.inject.Inject;
import jakarta.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class MovieProducer {

    @Inject @Channel("movies-out")
    Emitter<Record<Integer, String>> emitter;

    public void sendMovieToKafka(Movie movie) {
        emitter.send(Record.of(movie.getReleaseYear(), movie.getTitle()));
    }
}

/*package org.acme;

import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

public class MovieProducer {
    @Inject
    @Channel("movies-out")
    Emitter<Movie> emitter;

    //Logging
    public void sendMovie(Movie movie) {
        emitter.send(movie);
    }
}*/