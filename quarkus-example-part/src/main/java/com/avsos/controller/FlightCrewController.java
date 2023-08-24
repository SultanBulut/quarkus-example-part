package com.avsos.controller;

import com.avsos.dto.FlightCrewDTO;
import com.avsos.entity.FlightCrew;
import com.avsos.service.FlightCrewService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/flightcrew")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FlightCrewController {
    @Inject
    FlightCrewService flightCrewService;

    @GET
    public Response getAllFlightCrew(){
        List<FlightCrew> flightCrews=flightCrewService.getAllFlightCrew();
        return Response.ok(flightCrews).build();
    }

    @POST
    public Response createFlightCrew(FlightCrewDTO flightCrewDTO){
        if(flightCrewService.createFlightCrew(flightCrewDTO)){
            return Response.ok().build();
        }
        else{
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

    }
}
