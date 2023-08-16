package com.avsos.service;

import com.avsos.entity.Movie;
import com.avsos.dto.MovieDTO;
import com.avsos.kafka.MovieProducer;
import com.avsos.repository.MovieRepository;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class MovieService {

    @Inject
    MovieRepository movieRepository;
    @Inject
    MovieProducer movieProducer;

    public boolean createMovie(MovieDTO movieDto) {
        movieProducer.sendMovieToKafka(movieDto);

        return helper(movieDto);
    }

    @Transactional
    public boolean helper(MovieDTO movieDto) {
        Movie movie = new Movie(movieDto.getTitle(), movieDto.getReleaseYear(),
                movieDto.getMovieDirection(), movieDto.getMovieCast());
        movieRepository.persist(movie);
        return movieRepository.isPersistent(movie);
    }

    public Optional<Movie> getMovieByTitle(String title) {
        return movieRepository.findByTitle(title);
    }

    @Transactional
    public void updateMovie(String title,MovieDTO movieDto) {
        Optional<Movie> movieFromDatabase = movieRepository.findByTitle(title);
        movieFromDatabase.ifPresent(movie -> movie.setReleaseYear(movieDto.getReleaseYear()));
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

