package org.acme;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class MovieService {

    @Inject
    MovieRepository movieRepository;

    @Transactional
    public boolean createMovie(MovieDTO movieDto) {
        Movie movie = new Movie(movieDto.getTitle(), movieDto.getDirector(), movieDto.getReleaseYear());
        movieRepository.persist(movie);
        return movie.isPersistent();
    }


    @Transactional
    public Movie getMovieById(Long id) {
        return movieRepository.findById(id);
    }

    @Transactional
    public void updateMovie(Movie movie) {
        Movie movieFromDatabase = Movie.findMovieByTitle(movie.getTitle());
        movieFromDatabase.setDirector(movie.getDirector());
        movieFromDatabase.setReleaseYear(movie.getReleaseYear());
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

