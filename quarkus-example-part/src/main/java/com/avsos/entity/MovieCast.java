package com.avsos.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity(name = "movie_cast")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class MovieCast extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "act_id")
    private long actId;

    @Column(name = "mov_id")
    private long movId;

    @Column(name = "role_name")
    private String role;

}
