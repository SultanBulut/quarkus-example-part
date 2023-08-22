package com.avsos.service;

import com.avsos.debezium.MovieCreatedEvent;
import com.avsos.dto.ActorPayload;
import com.avsos.entity.Actor;
import com.avsos.entity.Director;
import com.avsos.entity.Movie;
import com.avsos.dto.MovieDTO;
import com.avsos.entity.MovieCast;
import com.avsos.kafka.MovieProducer;
import com.avsos.repository.ActorRepository;
import com.avsos.repository.DirectorRepository;
import com.avsos.repository.MovieCastRepository;
import com.avsos.repository.MovieRepository;
import io.debezium.outbox.quarkus.ExportedEvent;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Event;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class MovieService {

    @Inject
    MovieRepository movieRepository;
    @Inject
    DirectorRepository directorRepository;
    @Inject
    ActorRepository actorRepository;
    @Inject
    MovieCastRepository movieCastRepository;
    @Inject
    Event<ExportedEvent<?, ?>> event;

    @Inject
    MovieProducer movieProducer;

    public boolean createMovie(MovieDTO movieDTO) {
        movieProducer.sendMovieToKafka(movieDTO);

        return helper(movieDTO);
    }

    @Transactional
    public boolean helper(MovieDTO movieDTO) {
        Movie movie = new Movie(movieDTO.getTitle(), movieDTO.getReleaseYear());
        movie.assignDirectors(findDirectors(movieDTO.getDirIds()));
        movie.assignActors(findActors(movieDTO.getActorPayloads()));
        movieRepository.persist(movie);
        event.fire(MovieCreatedEvent.of(movie));
        assignRoles(movie.getMovId(), movieDTO.getActorPayloads());
        return movieRepository.isPersistent(movie);
    }

    public List<Director> findDirectors(List<Long> directorIds) {
        List<Director> result = new ArrayList<>();
        for (long id : directorIds) {
            result.add(directorRepository.findById(id));
        }
        return result;
    }

    public List<Actor> findActors(List<ActorPayload> actorPayloads) {
        List<Actor> result = new ArrayList<>();
        for (ActorPayload actorPayload : actorPayloads) {
            result.add(actorRepository.findById(actorPayload.getId()));
        }
        return result;
    }

    @Transactional
    public void assignRoles(long movId, List<ActorPayload> actorPayloads) {
        for (ActorPayload actorPayload : actorPayloads) {
            Optional<MovieCast> movieCast =
                    movieCastRepository.findByForeignKeys(movId, actorPayload.getId());
            movieCast.ifPresent(cast -> cast.setRole(actorPayload.getRole()));
        }
    }

    public Optional<Movie> getMovieByTitle(String title) {
        return movieRepository.findByTitle(title);
    }

    @Transactional
    public void updateMovie(String title,MovieDTO movieDTO) {
        Optional<Movie> movieFromDatabase = movieRepository.findByTitle(title);
        movieFromDatabase.ifPresent(movie -> movie.setReleaseYear(movieDTO.getReleaseYear()));
    }

    @Transactional
    public void deleteMovie(String title) {
        Optional<Movie> movie = movieRepository.findByTitle(title);
        movie.ifPresent(PanacheEntityBase::delete);
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll().list();
    }

}

