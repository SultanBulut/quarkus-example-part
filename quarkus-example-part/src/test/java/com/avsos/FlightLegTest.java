
/*package com.avsos;
import com.avsos.dto.FlightLegDTO;
import com.avsos.entity.AirCraft;
import com.avsos.entity.FlightCrew;
import com.avsos.entity.FlightLeg;
import com.avsos.kafka.FlightLegProducer;
import com.avsos.repository.AirCraftRepository;
import com.avsos.repository.FlightCrewRepository;
import com.avsos.repository.FlightRepository;
import com.avsos.service.FlightLegService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FlightLegTest {

    @Mock
    private FlightRepository flightRepository;

    @Mock
    private FlightCrewRepository flightCrewRepository;

    @Mock
    private AirCraftRepository airCraftRepository;

    @Mock
    private FlightLegProducer flightLegProducer;

    @InjectMocks
    private FlightLegService flightLegService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateFlightLeg() {
        FlightLegDTO flightLegDTO = new FlightLegDTO(// populate with test data );

        when(flightRepository.isPersistent(any(FlightLeg.class))).thenReturn(true);

        boolean result = flightLegService.createFlightLeg(flightLegDTO);

        assertTrue(result);
        verify(flightRepository).persist(any(FlightLeg.class));
    }

    @Test
    public void testFindFlightCrews() {
        List<Long> crewIds = Arrays.asList(1L, 2L, 3L);

        // Mock the behavior of the flightCrewRepository to return FlightCrew objects
        when(flightCrewRepository.findById(1L)).thenReturn(new FlightCrew(//populate with test data ));
        when(flightCrewRepository.findById(2L)).thenReturn(new FlightCrew(//populate with test data ));
        when(flightCrewRepository.findById(3L)).thenReturn(new FlightCrew(// populate with test data ));

        List<FlightCrew> flightCrews = flightLegService.findFlightCrews(crewIds);

        assertEquals(3, flightCrews.size());
        // Add more assertions as needed
    }

    // Add more test methods for other functionalities of FlightLegService
}
*/




//////////////////////////
/*package com.avsos;
import com.avsos.dto.FlightLegDTO;
import com.avsos.entity.AirCraft;
import com.avsos.entity.FlightCrew;
import com.avsos.entity.FlightLeg;
import com.avsos.repository.AirCraftRepository;
import com.avsos.repository.FlightCrewRepository;
import com.avsos.repository.FlightRepository;
import com.avsos.service.FlightLegService;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@QuarkusTest
public class FlightLegTest{
    @InjectMocks
    private FlightLegService flightLegService;

    @Mock
    private FlightRepository flightRepository;

    @Mock
    private FlightCrewRepository flightCrewRepository;
    @Mock
    private AirCraftRepository airCraftRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateFlightLeg() {
        FlightLegDTO flightLegDTO = new FlightLegDTO();
        flightLegDTO.setFlightNumber("FL123");
        flightLegDTO.setDepartureAirport("Airport1");
        flightLegDTO.setArrivalAirport("Airport2");
        flightLegDTO.setDepartureGate("GateA");
        flightLegDTO.setArrivalGate("GateB");
        flightLegDTO.setDepartureDate(new Date());

        // Set up mock behavior for findFlightCrews method
        when(flightCrewRepository.findById(anyLong())).thenReturn(new FlightCrew());

        // Set up mock behavior for findAirCrafts method
        when(airCraftRepository.findById(anyLong())).thenReturn(new AirCraft());

        // Set a list of crewIds and aircraftIds
        List<Long> crewIds = Arrays.asList(1L, 2L, 3L);
        List<Long> aircraftIds = Arrays.asList(4L, 5L, 6L);
        flightLegDTO.setCrewIds(crewIds);
        flightLegDTO.setAircraftIds(aircraftIds);

        // Set up mock behavior
        when(flightRepository.isPersistent(any())).thenReturn(true);

        boolean result = flightLegService.createFlightLeg(flightLegDTO);

        assertTrue(result);
        // Verify that persist method was called
        verify(flightRepository).persist(any(FlightLeg.class));
    }

    @Test
    public void testFindFlightCrews() {
        List<Long> crewIds = Arrays.asList(1L, 2L, 3L);
        // Set up mock behavior
        when(flightCrewRepository.findById(1L)).thenReturn(new FlightCrew());
        when(flightCrewRepository.findById(2L)).thenReturn(new FlightCrew());
        when(flightCrewRepository.findById(3L)).thenReturn(new FlightCrew());

        List<FlightCrew> flightCrews = flightLegService.findFlightCrews(crewIds);

        assertEquals(3, flightCrews.size());
        // Verify that findById method was called for each crewId
        verify(flightCrewRepository, times(3)).findById(anyLong());
    }

    @Test
    public void testFindAirCrafts() {
        List<Long> aircraftIds = Arrays.asList(4L, 5L, 6L);
        // Set up mock behavior
        when(airCraftRepository.findById(4L)).thenReturn(new AirCraft());
        when(airCraftRepository.findById(5L)).thenReturn(new AirCraft());
        when(airCraftRepository.findById(6L)).thenReturn(new AirCraft());

        List<AirCraft> airCrafts = flightLegService.findAirCrafts(aircraftIds);

        assertEquals(3, airCrafts.size());
        // Verify that findById method was called for each aircraftId
        verify(airCraftRepository, times(3)).findById(anyLong());
    }

    @Test
    public void testGetAllFlightLeg() {
        List<FlightLeg> flightLegs = Arrays.asList(new FlightLeg(), new FlightLeg());
        // Set up mock behavior
        //when(flightRepository.findAll().list()).thenReturn(flightLegs);

        // Set up mock behavior for flightRepository.findAll()
        PanacheQuery<FlightLeg> panacheQueryMock = Mockito.mock(PanacheQuery.class);
        when(flightRepository.findAll()).thenReturn(panacheQueryMock);
        when(panacheQueryMock.list()).thenReturn(flightLegs);

        List<FlightLeg> result = flightLegService.getAllFlightLeg();

        assertEquals(2, result.size());
        // Verify that findAll method was called
        verify(flightRepository).findAll();
    }

    @Test
    public void testGetFlightLegByFlightNumber() {
        String flightNumber = "FL123";
        FlightLeg flightLeg = new FlightLeg();
        // Set up mock behavior
        when(FlightLeg.findFlightLegByFlightNumber(flightNumber)).thenReturn(flightLeg);

        FlightLeg result = flightLegService.getFlightLegByFlightNumber(flightNumber);

        assertNotNull(result);
        // Verify that findFlightLegByFlightNumber method was called
        //verify(FlightLeg).findFlightLegByFlightNumber(flightNumber);
    }

    @Test
    public void testUpdateFlightLeg() {
        String flightNumber = "FL123";
        FlightLegDTO flightLegDTO = new FlightLegDTO();
        flightLegDTO.setArrivalAirport("NewAirport");

        // Set up mock behavior
        FlightLeg flightLeg = new FlightLeg();
        when(FlightLeg.findFlightLegByFlightNumber(flightNumber)).thenReturn(flightLeg);

        flightLegService.updateFlightLeg(flightNumber, flightLegDTO);

        assertEquals("NewAirport", flightLeg.getArrivalAirport());
    }

    @Test
    public void testDeleteFlightLeg() {
        String flightNumber = "FL123";
        FlightLeg flightLeg = new FlightLeg();
        // Set up mock behavior
        when(FlightLeg.findFlightLegByFlightNumber(flightNumber)).thenReturn(flightLeg);

        flightLegService.deleteFlightLeg(flightNumber);

        verify(flightLeg).delete();
    }

}
*/







package com.avsos;
import com.avsos.dto.FlightLegDTO;
import com.avsos.entity.AirCraft;
import com.avsos.entity.FlightCrew;
import com.avsos.entity.FlightLeg;
import com.avsos.kafka.FlightLegProducer;
import com.avsos.repository.AirCraftRepository;
import com.avsos.repository.FlightCrewRepository;
import com.avsos.repository.FlightRepository;
import com.avsos.service.FlightLegService;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@QuarkusTest
public class FlightLegTest {
    @InjectMocks
    private FlightLegService flightLegService;
    @Mock
    private FlightRepository flightRepository;
    @Mock
    private FlightLegProducer flightLegProducer;
    @Mock
    private FlightCrewRepository flightCrewRepository;
    @Mock
    private AirCraftRepository airCraftRepository;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void testCreateFlightLeg() {
        FlightLegDTO flightLegDTO = new FlightLegDTO();
        flightLegDTO.setFlightNumber("FL123");
        flightLegDTO.setDepartureAirport("Airport1");
        flightLegDTO.setArrivalAirport("Airport2");
        flightLegDTO.setDepartureGate("GateA");
        flightLegDTO.setArrivalGate("GateB");
        flightLegDTO.setDepartureDate(new Date());
        flightLegDTO.setCrewIds(Arrays.asList(1L, 2L, 3L));
        flightLegDTO.setAircraftId(4L);
        when(flightRepository.isPersistent(any())).thenReturn(true);
        boolean result = flightLegService.createFlightLeg(flightLegDTO);
        assertTrue(result);
        verify(flightLegProducer).sendFlightLegToKafka(flightLegDTO);
        verify(flightRepository).persist(any(FlightLeg.class));
    }

    @Test
    public void testHelper() {
        FlightLegDTO flightLegDTO = new FlightLegDTO();
        flightLegDTO.setFlightNumber("FL123");
        flightLegDTO.setDepartureAirport("Airport1");
        flightLegDTO.setArrivalAirport("Airport2");
        flightLegDTO.setDepartureGate("GateA");
        flightLegDTO.setArrivalGate("GateB");
        flightLegDTO.setDepartureDate(new Date());
        flightLegDTO.setCrewIds(Arrays.asList(1L, 2L, 3L));
        flightLegDTO.setAircraftId(4L);

        when(flightRepository.isPersistent(any(FlightLeg.class))).thenReturn(true);
        when(flightCrewRepository.findById(anyLong())).thenReturn(new FlightCrew());
        when(airCraftRepository.findById(anyLong())).thenReturn(new AirCraft());

        boolean result = flightLegService.helper(flightLegDTO);

        assertTrue(result);

        verify(flightRepository).persist(any(FlightLeg.class));
        verify(flightRepository, times(1)).persist(any(FlightLeg.class));
        verify(flightRepository, times(1)).isPersistent(any(FlightLeg.class));
    }

    @Test
    public void testFindFlightCrews() {
        List<Long> flightCrewIds = Arrays.asList(1L, 2L, 3L);
        when(flightCrewRepository.findById(1L)).thenReturn(new FlightCrew());
        when(flightCrewRepository.findById(2L)).thenReturn(new FlightCrew());
        when(flightCrewRepository.findById(3L)).thenReturn(new FlightCrew());

        List<FlightCrew> flightCrews = flightLegService.findFlightCrews(flightCrewIds);

        assertEquals(3, flightCrews.size());
    }

    @Test
    public void testFindAirCraft() {
        Long aircraftId = 4L;
        AirCraft expectedAircraft = new AirCraft();
        when(airCraftRepository.findById(aircraftId)).thenReturn(expectedAircraft);
        AirCraft result = flightLegService.findAirCraft(aircraftId);
        assertNotNull(result);
        assertSame(expectedAircraft, result);
    }


    @Test
    public void testGetAllFlightLeg() {
        List<FlightLeg> flightLegs = Arrays.asList(new FlightLeg(), new FlightLeg());
        PanacheQuery<FlightLeg> panacheQueryMock = Mockito.mock(PanacheQuery.class);
        when(flightRepository.findAll()).thenReturn(panacheQueryMock);
        when(panacheQueryMock.list()).thenReturn(flightLegs);
        List<FlightLeg> result = flightLegService.getAllFlightLeg();
        assertEquals(2, result.size());
        verify(flightRepository).findAll();
    }

    @Test
    public void testGetFlightLegByFlightLegId() {
        String flightLegId ="3";
        FlightLeg flightLeg = new FlightLeg(flightLegId,"FL123", "Airport1", "Airport2", "Gate1", "Gate2", new Date());
        when(flightRepository.findByFlightLegId(flightLegId)).thenReturn(Optional.of(flightLeg));
        FlightLeg result = flightLegService.getFlightLegByFlightLegID(flightLegId);
        assertEquals(flightLegId, result.getFlightLegId());
        assertEquals("FL123", result.getFlightNumber());

    }

}







//assertNotNull(result);
