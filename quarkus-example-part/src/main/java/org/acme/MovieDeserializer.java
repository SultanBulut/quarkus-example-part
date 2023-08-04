package org.acme;

import io.quarkus.kafka.client.serialization.ObjectMapperDeserializer;
import org.acme.Movie;

public class MovieDeserializer extends ObjectMapperDeserializer<Movie> {
    public MovieDeserializer() {
        super(Movie.class);
    }
}



/*package org.acme;

import io.quarkus.kafka.client.serialization.ObjectMapperDeserializer;
import org.acme.Movie;

public class MovieDeserializer extends ObjectMapperDeserializer<Movie> {
    public MovieDeserializer() {
        super(Movie.class);
    }
} */