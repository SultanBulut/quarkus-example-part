package com.avsos.controller;

import com.avsos.dto.FlightLegDTO;
import com.avsos.dto.SimplifiedFlightLegDTO;
import com.avsos.entity.FlightLeg;
import com.avsos.service.FlightLegService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
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
    @Path("/{simplifiedflightleg}")
    public Response getAllSimplifiedFlightLegs() {
        List<FlightLeg> flightLegs = flightLegService.getAllFlightLeg();
        List<SimplifiedFlightLegDTO> simplifiedFlightLegs = flightLegService.convertToSimplifiedDTOs(flightLegs);
        return Response.ok(simplifiedFlightLegs).build();
    }

    @GET
    @Path("/{simplifiedflightleg}/{flightLegId}")
    public Response getAllSimplifiedFlightLegsByFlightLegID(@PathParam("flightLegId")String flightLegId) {
       FlightLeg flightLeg= flightLegService.getFlightLegByFlightLegID(flightLegId);
        SimplifiedFlightLegDTO simplifiedFlightLeg= flightLegService.convertToSimplifiedDTO(flightLeg);
        if(simplifiedFlightLeg!=null){
            return Response.ok(simplifiedFlightLeg).build();
        }else{
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    // Overloaded method to convert a single FlightLeg to SimplifiedFlightLegDTO
    /*public SimplifiedFlightLegDTO convertToSimplifiedDTO(FlightLeg flightLeg) {
        SimplifiedFlightLegDTO simplifiedDTO = new SimplifiedFlightLegDTO();
        simplifiedDTO.setFlightLegId(flightLeg.getFlightLegId());
        simplifiedDTO.setFlightNumber(flightLeg.getFlightNumber());
        simplifiedDTO.setDepartureAirport(flightLeg.getDepartureAirport());
        simplifiedDTO.setArrivalAirport(flightLeg.getArrivalAirport());
        simplifiedDTO.setDepartureGate(flightLeg.getDepartureGate());
        simplifiedDTO.setArrivalGate(flightLeg.getArrivalGate());
        simplifiedDTO.setDepartureDate(flightLeg.getDepartureDate());
        return simplifiedDTO;
    }

    // Overloaded method to convert a list of FlightLegs to SimplifiedFlightLegDTOs
    public List<SimplifiedFlightLegDTO> convertToSimplifiedDTOs(List<FlightLeg> flightLegs) {
        List<SimplifiedFlightLegDTO> simplifiedFlightLegs = new ArrayList<>();
        for (FlightLeg flightLeg : flightLegs) {
            simplifiedFlightLegs.add(convertToSimplifiedDTO(flightLeg));
        }
        return simplifiedFlightLegs;
    }*/


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
