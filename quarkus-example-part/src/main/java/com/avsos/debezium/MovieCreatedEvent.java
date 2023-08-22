package com.avsos.debezium;

import com.avsos.entity.Movie;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.debezium.outbox.quarkus.ExportedEvent;

import java.time.Instant;
import java.util.Collections;
import java.util.Map;

public class MovieCreatedEvent implements ExportedEvent<String, JsonNode> {

    private static final ObjectMapper mapper = new ObjectMapper();

    private static final String TYPE = "movie";
    private static final String EVENT_TYPE = "movieCreated";

    private final long id;
    private final JsonNode movie;
    private final Instant timestamp;

    public MovieCreatedEvent(long id, JsonNode movie) {
        this.id = id;
        this.movie = movie;
        this.timestamp = Instant.now();
    }

    public static MovieCreatedEvent of(Movie movie) {
        ObjectNode asJson = mapper.createObjectNode()
                .put("id", movie.getMovId())
                .put("title", movie.getTitle())
                .put("releaseYear", movie.getReleaseYear());

        return new MovieCreatedEvent(movie.getMovId(), asJson);
    }

    @Override
    public String getAggregateId() {
        return String.valueOf(id);
    }

    @Override
    public String getAggregateType() {
        return TYPE;
    }

    @Override
    public JsonNode getPayload() {
        return movie;
    }

    @Override
    public String getType() {
        return EVENT_TYPE;
    }

    @Override
    public Instant getTimestamp() {
        return timestamp;
    }

    @Override
    public Map<String, Object> getAdditionalFieldValues() {
        return Collections.emptyMap();
    }
}