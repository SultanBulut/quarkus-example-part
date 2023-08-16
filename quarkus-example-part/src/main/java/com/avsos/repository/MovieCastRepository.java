package com.avsos.repository;

import com.avsos.entity.MovieCast;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class MovieCastRepository implements PanacheRepository<MovieCast> {

    public Optional<MovieCast> findByForeignKeys(long movId, long actId) {
        return find("FROM movie_cast WHERE movId=?1 AND actId=?2 ", movId, actId).firstResultOptional();
    }
}
