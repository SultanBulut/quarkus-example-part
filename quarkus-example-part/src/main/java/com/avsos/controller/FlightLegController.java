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
    @Path("/{flightNumber}")
    public Response getFlightLegsByFlightNumber(@PathParam("flightNumber")String flightNumber){
        FlightLeg flightLeg= flightLegService.getFlightLegByFlightNumber(flightNumber);
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
    @Path("/{flightNumber}")
    public Response updateFlightLeg(@PathParam("flightNumber")String flightNumber,FlightLegDTO flightLegDTO){
        flightLegService.updateFlightLeg(flightNumber,flightLegDTO);
        return Response.ok(flightLegDTO).build();
    }

    @DELETE
    @Path("/{flightNumber}")
    public Response deleteFlightLeg(@PathParam("flightNumber")String flightNumber){
        flightLegService.deleteFlightLeg(flightNumber);
        return Response.noContent().build();
    }
}
