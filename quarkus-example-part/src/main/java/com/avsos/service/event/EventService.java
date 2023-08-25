package com.avsos.service.event;

import com.avsos.entity.event.Event;
import com.avsos.repository.event.EventRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class EventService {

    private static final Logger log = LoggerFactory.getLogger(EventService.class);

    @Inject
    EventRepository eventRepository;

    public void addEvent(Event event) {
        eventRepository.persist(event).
                onItem().transform(r -> r)
                .subscribe().with(
                        result -> log.debug("inserted :{}", result),
                        failure -> log.warn("fail insert: :{}" + failure));
    }

}
