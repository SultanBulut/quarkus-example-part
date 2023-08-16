package com.avsos.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Movie extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(name = "release_year")
    private int releaseYear;

    public Movie(String title, int releaseYear, List<MovieDirection> movieDirection, List<MovieCast> movieCast) {
        this.title = title;
        this.releaseYear = releaseYear;
        this.movieDirection=movieDirection;
        this.movieCast=movieCast;
    }

    public static Movie findMovieByTitle(String title) {
        return find("title", title).firstResult();
    }

    @OneToMany(mappedBy = "movie",cascade = CascadeType.ALL)
    private List<MovieDirection> movieDirection;

    @OneToMany(mappedBy = "movie",cascade = CascadeType.ALL)
    private List<MovieCast> movieCast;


}



