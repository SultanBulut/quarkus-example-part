/*package org.acme;


import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MovieProcessor {

    @Incoming("movies-in")
    public void consume(String message) {
        System.out.println("Received message: " + message);

    }
    @Outgoing("movie-out")
    public String produce() {
        String message = "Hello from MovieProcessor";
        System.out.println("Sending message: " + message);
        return message;
    }

    // @Incoming("movies-in")
    //@Outgoing("movies-out")
    // public String process(String title) {
    //     return title;
    // }
}*/
