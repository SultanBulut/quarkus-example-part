package com.avsos.controller;

import com.avsos.dto.FlightLegDTO;
import com.avsos.entity.FlightLeg;
import com.avsos.service.FlightLegService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/flight")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FlightLegController {
    @Inject
    FlightLegService flightLegService;

    @GET
    public Response getAllFlightLegs() {
        List<FlightLeg> flightLegs = flightLegService.getAllFlightLeg();
        return Response.ok(flightLegs).build();
    }

    @GET
    @Path("/{flightLegId}")
    public Response getFlightLegsByFlightLegId(@PathParam("flightLegId")String flightLegId){
        FlightLeg flightLeg= flightLegService.getFlightLegByFlightLegID(flightLegId);
        if(flightLeg!=null){
            return Response.ok(flightLeg).build();
        }else{
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    public Response createFlightLegs(FlightLegDTO flightLegDTO){
        if(flightLegService.createFlightLeg(flightLegDTO)){
            return Response.ok().build();
        }else{
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @PUT
    @Path("/{flightLegId}")
    public Response updateFlightLeg(@PathParam("flightLegId") String flightLegId,FlightLegDTO flightLegDTO){
     flightLegService.updateFlightLeg(flightLegId,flightLegDTO);
     return Response.ok(flightLegDTO).build();
    }

    @DELETE
    @Path("/{flightLegId}")
    public Response deleteFlightLeg(@PathParam("flightLegId")String flightLegId){
        flightLegService.deleteFlightLeg(flightLegId);
        return Response.noContent().build();
    }



}
