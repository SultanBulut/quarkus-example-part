package com.avsos.repository.event;


import com.avsos.entity.event.Event;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoRepository;

public interface EventRepository extends ReactivePanacheMongoRepository<Event> {

}
