package com.avsos.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class FlightLegDTO {

    private String flightLegId;
    private String flightNumber;
    private String departureAirport;
    private String arrivalAirport;
    private String departureGate;
    private String arrivalGate;
   // @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private Date departureDate;
    private List<Long> crewIds;
    private Long aircraftId;
   // private List<Long> aircraftIds;
}
