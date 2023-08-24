package com.avsos.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class FlightCrewDTO {

    private String crewName;
    private String crewPosition;
    private String state;
    private String dutyType;
}
