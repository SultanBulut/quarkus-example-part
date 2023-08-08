
package org.acme;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.apache.camel.builder.RouteBuilder;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MovieConsumer extends RouteBuilder {

    @Inject
    MovieRepository movieRepository;
    @Override
    public void configure() throws Exception {
        from("kafka:movies-in")
                .unmarshal().json(MovieDTO.class)
                .bean(this, "processMovie");
    }

    @Transactional
    public void processMovie(MovieDTO movieDTO) {
        Movie movie = new Movie(movieDTO.getTitle(), movieDTO.getDirector(), movieDTO.getReleaseYear());
        movieRepository.persist(movie);
    }
}
