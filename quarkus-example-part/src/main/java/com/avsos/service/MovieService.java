package com.avsos.service;

import com.avsos.entity.Actor;
import com.avsos.entity.Director;
import com.avsos.entity.Movie;
import com.avsos.dto.MovieDTO;
import com.avsos.kafka.MovieProducer;
import com.avsos.repository.ActorRepository;
import com.avsos.repository.DirectorRepository;
import com.avsos.repository.MovieRepository;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.enterprise.context.ApplicationScoped;
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
    MovieProducer movieProducer;

    public boolean createMovie(MovieDTO movieDTO) {
        movieProducer.sendMovieToKafka(movieDTO);

        return helper(movieDTO);
    }

    @Transactional
    public boolean helper(MovieDTO movieDTO) {
        Movie movie = new Movie(movieDTO.getTitle(), movieDTO.getReleaseYear());
        movie.assignDirectors(findDirectors(movieDTO.getDirIds()));
        movie.assignActors(findActors(movieDTO.getActIds()));
        movieRepository.persist(movie);
        return movieRepository.isPersistent(movie);
    }

    public List<Director> findDirectors(List<Long> directorIds) {
        List<Director> result = new ArrayList<>();
        for (long id : directorIds) {
            result.add(directorRepository.findById(id));
        }
        return result;
    }

    public List<Actor> findActors(List<Long> actorIds) {
        List<Actor> result = new ArrayList<>();
        for (long id : actorIds) {
            result.add(actorRepository.findById(id));
        }
        return result;
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

