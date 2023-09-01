package com.avsos;

import com.avsos.dto.FlightCrewDTO;
import com.avsos.entity.FlightCrew;
import com.avsos.service.FlightCrewService;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
public class FlightCrewTest {
    @Inject
    FlightCrewService flightCrewService;

    @Test
    public void testCreateFlightCrew(){
        FlightCrewDTO flightCrewDTO= new FlightCrewDTO();
        flightCrewDTO.setCrewName("John Doe");
        flightCrewDTO.setCrewPosition("Pilot");
        flightCrewDTO.setState("Active");
        flightCrewDTO.setDutyType("Captain");

        boolean result = flightCrewService.createFlightCrew(flightCrewDTO);

        assertTrue(result, "Failed to create a flight crew");
    }

    @Test
    public void testGetAllFlightCrew(){
        List<FlightCrew> flightCrewList= flightCrewService.getAllFlightCrew();

        assertNotNull(flightCrewList,"Flight crew list should not be null");
        assertNotEquals(0, flightCrewList.size(), "Flight crew list should not be empty");

    }

} 
