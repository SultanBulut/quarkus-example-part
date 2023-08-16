package org.acme;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/actors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ActorController {
    @Inject
    ActorService actorService;

    @GET
    public Response getALLActors(){
        List<Actor> actors= actorService.getAllActors();
        return Response.ok(actors).build();
    }

    @POST
    public Response createActors(Actor actor){
        if(actorService.createActor(actor)){
            return Response.ok(actor).build();
        }else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
