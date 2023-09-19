/*package com.avsos;

import com.avsos.dto.AirCraftDTO;
import com.avsos.entity.AirCraft;
import com.avsos.service.AirCraftService;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
public class AirCraftTest {
    @Inject
    AirCraftService airCraftService;

    @Test
    public void testCreateAirCraft() {
        AirCraftDTO airCraftDTO = new AirCraftDTO();
        airCraftDTO.setAcType("Type");
        airCraftDTO.setSubtype("Subtype");
        airCraftDTO.setCustomizedType("Custom Type");
        airCraftDTO.setNameOfAircraft("Aircraft 1");

        boolean result = airCraftService.createAirCraft(airCraftDTO);

        assertTrue(result, "Failed to create an aircraft");
    }

    @Test
    public void testGetAllAirCrafts() {
        List<AirCraft> airCraftList = airCraftService.getALLAirCraft();

        assertNotNull(airCraftList, "Aircraft list should not be null");
        assertNotEquals(0, airCraftList.size(), "Aircraft list should not be empty");
    }

}*/
package com.avsos;

import com.avsos.dto.AirCraftDTO;
import com.avsos.entity.AirCraft;
import com.avsos.repository.AirCraftRepository;
import com.avsos.service.AirCraftService;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;
@QuarkusTest
public class AirCraftTest {

    @InjectMocks
    private AirCraftService airCraftService;

    @Mock
    private AirCraftRepository airCraftRepository;

    @BeforeEach
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateAirCraft() {
        AirCraftDTO airCraftDTO = new AirCraftDTO();
        airCraftDTO.setAcType("Type");
        airCraftDTO.setSubtype("Subtype");
        airCraftDTO.setCustomizedType("Custom Type");
        airCraftDTO.setNameOfAircraft("Aircraft 1");


        when(airCraftRepository.isPersistent(any(AirCraft.class))).thenReturn(true);


        boolean result = airCraftService.createAirCraft(airCraftDTO);


        verify(airCraftRepository).persist(any(AirCraft.class));


        assertTrue(result, "Failed to create an aircraft");
    }

    @Test
    public void testGetAllAirCrafts() {
        List<AirCraft> airCraftList = new ArrayList<>();
        airCraftList.add(new AirCraft());

        PanacheQuery<AirCraft> panacheQuery = mock(PanacheQuery.class);
        when(airCraftRepository.findAll()).thenReturn(panacheQuery);
        when(panacheQuery.list()).thenReturn(airCraftList);


        List<AirCraft> result = airCraftService.getALLAirCraft(); // Call the getALLAirCraft method

        // Verify the result
        assertNotNull(result, "Aircraft list should not be null");
        assertNotEquals(0, result.size(), "Aircraft list should not be empty");
        assertEquals(airCraftList, result, "Returned aircraft list doesn't match the expected list");
    }
}
