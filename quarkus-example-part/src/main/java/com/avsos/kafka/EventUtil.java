package com.avsos.kafka;

import com.avsos.entity.event.Event;
import com.avsos.service.event.EventService;
import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.apache.camel.Exchange;

@ApplicationScoped
@Named("EventUtil")
@RegisterForReflection
public class EventUtil {

    @Inject
    EventService eventService;

    public void createEvent(Exchange exchange) {
        Event event = new Event();
        event.setEventId(exchange.getIn().getHeader("ce_id", String.class));
        event.setMessageContent(exchange.getIn().getBody(String.class));
        eventService.addEvent(event);
    }

}
