package com.avsos.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Movie extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mov_id")
    private Long movId;

    private String title;
    @Column(name = "release_year")
    private int releaseYear;

    @ManyToMany
    @JoinTable(name = "movie_direction", joinColumns = @JoinColumn(name = "mov_id"),
            inverseJoinColumns = @JoinColumn(name = "dir_id"))
    private List<Director> directors = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "movie_cast", joinColumns = @JoinColumn(name = "mov_id"),
            inverseJoinColumns = @JoinColumn(name = "act_id"))
    private List<Actor> actors = new ArrayList<>();

    public Movie(String title, int releaseYear) {
        this.title = title;
        this.releaseYear = releaseYear;
    }

    public void assignDirectors(List<Director> directors) {
        this.directors.addAll(directors);
    }

    public void assignActors(List<Actor> actors) {
        this.actors.addAll(actors);
    }

}



