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
public class Actor extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastname;
    private String gender;

    public Actor(String name,String lastname,String gender){
        this.name=name;
        this.lastname=lastname;
        this.gender=gender;
    }

    @OneToMany(mappedBy = "actor", cascade = CascadeType.ALL)
    private List<MovieCast> movieCasts;

}
