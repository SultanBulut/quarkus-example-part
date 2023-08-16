
package com.avsos.kafka;

import com.avsos.entity.Movie;
import com.avsos.dto.MovieDTO;
import com.avsos.repository.MovieRepository;
import com.avsos.service.MovieService;
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
    @Inject
    MovieService movieService;

    @Override
    public void configure() throws Exception {
        from("kafka:movies-in")
                .log("received: ${body}")
                .unmarshal().json(JsonLibrary.Jackson, MovieDTO.class)
                .bean(this, "processMovie")
                .marshal().json()
                .log("Sent: ${body}")
                .to("kafka:movies-out");
    }

    @Transactional
    public Movie processMovie(MovieDTO movieDTO) {
        Optional<Movie> optionalMovie = movieRepository.findByTitle(movieDTO.getTitle());
        if (optionalMovie.isEmpty()) {
            Movie movie = new Movie(movieDTO.getTitle(), movieDTO.getReleaseYear());
            movie.assignDirectors(movieService.findDirectors(movieDTO.getDirIds()));
            movie.assignActors(movieService.findActors(movieDTO.getActorPayloads()));
            movieRepository.persist(movie);
            //movie.setReleaseYear(1230);
            movieService.assignRoles(movie.getMovId(), movieDTO.getActorPayloads());
            return movie;

        } else {
            Movie existingMovie = optionalMovie.get();
            existingMovie.setReleaseYear(movieDTO.getReleaseYear());
            return existingMovie;
        }
    }

}