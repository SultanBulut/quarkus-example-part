package com.avsos.entity.event;

import io.quarkus.mongodb.panache.common.MongoEntity;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@MongoEntity(collection = "event")
public class Event extends ReactivePanacheMongoEntity {

    private String eventId;
    private String messageContent;

}
