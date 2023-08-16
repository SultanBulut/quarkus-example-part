package com.avsos.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Director extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dir_id")
    private Long dirId;

    private String name;
    private String lastname;

    @JsonIgnore
    @ManyToMany(mappedBy = "directors")
    private List<Movie> movies = new ArrayList<>();

    public Director(String name,String lastname){
        this.name=name;
        this.lastname=lastname;
    }

}
