package org.acme;


import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MovieProcessor {

    private static final double CONVERSION_RATE = 0.88;

    @Incoming("movie")
    @Outgoing("movie-out")
    public String process(String title) {
        return title;
    }

}