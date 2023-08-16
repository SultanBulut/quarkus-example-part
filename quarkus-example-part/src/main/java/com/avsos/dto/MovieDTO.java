package com.avsos.dto;

import lombok.Data;

import java.util.List;

@Data
public class MovieDTO {

    private String title;
    private int releaseYear;
    private List<Long> dirIds;
    private List<ActorPayload> actorPayloads;

}
