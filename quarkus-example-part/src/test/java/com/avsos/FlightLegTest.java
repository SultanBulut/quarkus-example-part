package com.avsos;

import com.avsos.dto.FlightLegDTO;
import com.avsos.entity.FlightLeg;
import com.avsos.service.FlightLegService;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
public class FlightLegTest {

    @Inject
    FlightLegService flightLegService;

    @Test
    public void testCreateFlightLeg() {
        FlightLegDTO flightLegDTO = new FlightLegDTO();
        flightLegDTO.setFlightNumber("FL123");
        flightLegDTO.setDepartureAirport("Airport A");
        flightLegDTO.setArrivalAirport("Airport B");
        flightLegDTO.setDepartureGate("Gate A");
        flightLegDTO.setArrivalGate("Gate B");
        flightLegDTO.setDepartureDate(new Date());

        List<Long> flightCrewIds = new ArrayList<>();
        flightCrewIds.add(1L);
        flightCrewIds.add(2L);
        flightLegDTO.setCrewIds(flightCrewIds);

        List<Long> aircraftIds = new ArrayList<>();
        aircraftIds.add(1L);
        aircraftIds.add(2L);
        flightLegDTO.setAircraftIds(aircraftIds);



        boolean result = flightLegService.createFlightLeg(flightLegDTO);

        assertTrue(result, "Failed to create a FlightLeg");
    }

    /*@Test
    public void testGetFlightLegByFlightNumber() {
        FlightLegDTO flightLegDTO = new FlightLegDTO();
        flightLegDTO.setFlightNumber("FL123");
        flightLegDTO.setDepartureAirport("Airport A");
        flightLegDTO.setArrivalAirport("Airport B");
        flightLegDTO.setDepartureGate("Gate A");
        flightLegDTO.setArrivalGate("Gate B");
        flightLegDTO.setDepartureDate(new Date());

        flightLegService.createFlightLeg(flightLegDTO);


        FlightLeg flightLeg = flightLegService.getFlightLegByFlightNumber("FL123");

        assertNotNull(flightLeg, "FlightLeg with flight number FL123 not found");
    }

    @Test
    public void testGetAllFlightLegs() {
        List<FlightLeg> flightLegList = flightLegService.getAllFlightLeg();

        assertNotNull(flightLegList, "FlightLeg list should not be null");
        assertNotEquals(0, flightLegList.size(), "FlightLeg list should not be empty");
    }

   @Test
    public void testUpdateFlightLeg() {

        FlightLegDTO flightLegDTO = new FlightLegDTO();
        flightLegDTO.setFlightNumber("FL123");
        flightLegDTO.setDepartureAirport("Airport A");
        flightLegDTO.setArrivalAirport("Airport B");
        flightLegDTO.setDepartureGate("Gate A");
        flightLegDTO.setArrivalGate("Gate B");
        flightLegDTO.setDepartureDate(new Date());

        flightLegService.createFlightLeg(flightLegDTO);


        FlightLegDTO updatedFlightLegDTO = new FlightLegDTO();
        updatedFlightLegDTO.setArrivalAirport("New Airport X");
        updatedFlightLegDTO.setDepartureAirport("New Airport Y");
        updatedFlightLegDTO.setArrivalGate("New Gate X");
        updatedFlightLegDTO.setDepartureGate("New Gate Y");
        updatedFlightLegDTO.setDepartureDate(new Date());

        flightLegService.updateFlightLeg("FL123", updatedFlightLegDTO);


        FlightLeg updatedFlightLeg = flightLegService.getFlightLegByFlightNumber("FL123");


        assertNotNull(updatedFlightLeg, "Updated FlightLeg not found");
        assertEquals("New Airport X", updatedFlightLeg.getArrivalAirport(), "Arrival airport not updated");
        assertEquals("New Airport Y", updatedFlightLeg.getDepartureAirport(), "Departure airport not updated");
        assertEquals("New Gate X", updatedFlightLeg.getArrivalGate(), "Arrival gate not updated");
        assertEquals("New Gate Y", updatedFlightLeg.getDepartureGate(), "Departure gate not updated");
    }*/



}
