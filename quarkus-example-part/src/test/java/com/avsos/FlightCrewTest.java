/*package com.avsos;

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

} */
package com.avsos;

import com.avsos.dto.FlightCrewDTO;
import com.avsos.entity.FlightCrew;
import com.avsos.repository.FlightCrewRepository;
import com.avsos.service.FlightCrewService;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;

@QuarkusTest
public class FlightCrewTest {

    @Mock
    private FlightCrewService flightCrewService;

    @Mock
    private FlightCrewRepository flightCrewRepository;


    @BeforeEach
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateFlightCrew() {
        FlightCrewDTO flightCrewDTO = new FlightCrewDTO();
        flightCrewDTO.setCrewName("John Doe");
        flightCrewDTO.setCrewPosition("Pilot");
        flightCrewDTO.setState("Active");
        flightCrewDTO.setDutyType("Captain");


        when(flightCrewRepository.isPersistent(any(FlightCrew.class))).thenReturn(true);

        boolean result = flightCrewService.createFlightCrew(flightCrewDTO);


        verify(flightCrewRepository).persist(any(FlightCrew.class));

        assertTrue(result, "Failed to create a flight crew");
    }

    @Test
    public void testGetAllFlightCrew() {
        List<FlightCrew> flightCrewList = new ArrayList<>();
        flightCrewList.add(new FlightCrew());


        PanacheQuery<FlightCrew> panacheQuery = mock(PanacheQuery.class);
        when(flightCrewRepository.findAll()).thenReturn(panacheQuery);
        when(panacheQuery.list()).thenReturn(flightCrewList);

        List<FlightCrew> result = flightCrewService.getAllFlightCrew();


        assertNotNull(result, "Flight crew list should not be null");
        assertNotEquals(0, result.size(), "Flight crew list should not be empty");
        assertEquals(flightCrewList, result, "Returned flight crew list doesn't match the expected list");
    }
}

