package com.avsos.controller;

import com.avsos.dto.DirectorDTO;
import com.avsos.entity.Director;
import com.avsos.service.DirectorService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/director")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DirectorController {
    @Inject
    DirectorService directorService;

    @GET
    public Response getAllDirectors(){
        List<Director> movies = directorService.getAllDirectors();
        return Response.ok(movies).build();
    }

    @POST
    public Response createDirector(DirectorDTO directorDTO){
        if(directorService.createDirector(directorDTO)){
            return Response.ok().build();
        }else{
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
