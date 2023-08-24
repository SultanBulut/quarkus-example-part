package com.avsos.controller;

import com.avsos.dto.AirCraftDTO;
import com.avsos.entity.AirCraft;
import com.avsos.service.AirCraftService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/aircraft")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AirCraftController {
    @Inject
    AirCraftService airCraftService;

    @GET
    public Response getAllAirCrafts(){
        List<AirCraft> airCrafts =airCraftService.getALLAirCraft();
        return Response.ok(airCrafts).build();
    }

    @POST
    public Response createAirCraft(AirCraftDTO airCraftDTO){
        if(airCraftService.createAirCraft(airCraftDTO)){
            return Response.ok(airCraftDTO).build();
        }else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

}
