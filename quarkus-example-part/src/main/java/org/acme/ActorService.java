package org.acme;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class ActorService {
    @Inject
    ActorRepository actorRepository;

    @Transactional
    public boolean createActor(Actor actor){
        Actor newactor = new Actor(actor.getName(),actor.getLastname(),actor.getGender());
        actorRepository.persist(newactor);
        return actorRepository.isPersistent(newactor);
    }

    @Transactional
    public List<Actor> getAllActors(){
        return actorRepository.findAll().list();
    }
}
