package com.avsos;

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

}
