package com.avsos.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class FlightLegDTO {
    private String flightNumber;
    private String departureAirport;
    private String arrivalAirport;
    private String departureGate;
    private String arrivalGate;
    private Date departureDate;
    private List<Long> crewIds;
    private List<Long> aircraftIds;
}
