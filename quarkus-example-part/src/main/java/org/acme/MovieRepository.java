package org.acme;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class MovieRepository implements PanacheRepository<Movie> {

    Optional<Movie> findByTitle(String title){
        return find(" FROM Movie where title=?1 ",title).firstResultOptional();
    }
}
