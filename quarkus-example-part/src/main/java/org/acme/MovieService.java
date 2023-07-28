/*package org.acme;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@ApplicationScoped
public class MovieService {

    @PersistenceContext
    private EntityManager entityManager;


    public List<Movie> getAllMovies() {
        return entityManager.createQuery("SELECT m FROM Movie m", Movie.class).getResultList();
    }

    public Movie getMovieById(Long id) {
        return entityManager.find(Movie.class, id);
    }

    public List<Movie> getMoviesByCountry(String country) {
        return entityManager.createQuery("SELECT m FROM Movie m WHERE m.country = :country", Movie.class)
                .setParameter("country", country)
                .getResultList();
    }

    public Movie getMovieByTitle(String title) {
        return entityManager.createQuery("SELECT m FROM Movie m WHERE m.title = :title", Movie.class)
                .setParameter("title", title)
                .getSingleResult();
    }

    public void createMovie(Movie movie) {
        entityManager.persist(movie);
    }

    public boolean deleteMovieById(Long id) {
        Movie movie = entityManager.find(Movie.class, id);
        if (movie != null) {
            entityManager.remove(movie);
            return true;
        }
        return false;
    }

}*/
package org.acme;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import java.util.List;

@ApplicationScoped
public class MovieService {

    @Inject
    EntityManager entityManager;

    @Transactional
    public void createMovie(Movie movie) {
       entityManager.persist(movie);
    }


    @Transactional
    public Movie getMovieById(Long id) {
        return entityManager.find(Movie.class, id);
    }

    @Transactional
    public void updateMovie(Movie movie) {
        entityManager.merge(movie);
    }

    @Transactional
    public void deleteMovie(Long id) {
        Movie movie = entityManager.find(Movie.class, id);
        if (movie != null) {
            entityManager.remove(movie);
        }
    }

    @Transactional
    public List<Movie> getAllMovies() {
        return entityManager.createQuery("SELECT m FROM Movie m", Movie.class).getResultList();
    }
}

