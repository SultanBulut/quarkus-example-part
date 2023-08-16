package com.avsos.controller;

import com.avsos.dto.ActorDTO;
import com.avsos.entity.Actor;
import com.avsos.service.ActorService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/actor")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ActorController {
    @Inject
    ActorService actorService;

    @GET
    public Response getAllActors(){
        List<Actor> actors= actorService.getAllActors();
        return Response.ok(actors).build();
    }

    @POST
    public Response createActor(ActorDTO actorDTO){
        if(actorService.createActor(actorDTO)){
            return Response.ok().build();
        }else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
