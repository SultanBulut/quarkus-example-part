package org.acme;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

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
        Movie movie = new Movie(movieDto.getTitle(), movieDto.getDirector(), movieDto.getReleaseYear());
        movieRepository.persist(movie);
        return movieRepository.isPersistent(movie);
    }


    @Transactional
    public Movie getMovieByTitle(String title) {
        return Movie.findMovieByTitle(title);
    }

    @Transactional
    public void updateMovie(String title,MovieDTO movieDto) {
        Movie movieFromDatabase = Movie.findMovieByTitle(title);
        movieFromDatabase.setDirector(movieDto.getDirector());
        movieFromDatabase.setReleaseYear(movieDto.getReleaseYear());
    }

    @Transactional
    public void deleteMovie(String title) {
        Movie movie = Movie.findMovieByTitle(title);
        if (movie != null) {
            movie.delete();
        }
    }

    @Transactional
    public List<Movie> getAllMovies() {
        return movieRepository.findAll().list();
    }
}

