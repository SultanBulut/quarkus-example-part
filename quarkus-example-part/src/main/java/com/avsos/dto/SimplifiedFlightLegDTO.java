package com.avsos.dto;

import lombok.Data;

import java.util.Date;

@Data
public class SimplifiedFlightLegDTO {
    private String flightLegId;
    private String flightNumber;
    private String departureAirport;
    private String arrivalAirport;
    private String departureGate;
    private String arrivalGate;
    private Date departureDate;
}
