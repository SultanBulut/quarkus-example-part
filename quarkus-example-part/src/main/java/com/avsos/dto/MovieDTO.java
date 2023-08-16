package com.avsos.dto;

import com.avsos.entity.MovieCast;
import com.avsos.entity.MovieDirection;
import lombok.Data;

import java.util.List;

@Data
public class MovieDTO {

    private String title;

    private int releaseYear;

    private List<MovieDirection> movieDirection;
    private List<MovieCast> movieCast;

}
