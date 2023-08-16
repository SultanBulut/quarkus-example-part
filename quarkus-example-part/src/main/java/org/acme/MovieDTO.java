package org.acme;

import lombok.Data;

import java.util.List;

@Data
public class MovieDTO {

    private String title;

    private int releaseYear;

    //private String director;

    private List<MovieDirection> movieDirection;
    private List<MovieCast> movieCast;

}
