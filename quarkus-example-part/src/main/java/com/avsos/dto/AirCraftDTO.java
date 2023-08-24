package com.avsos.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class AirCraftDTO {

    private String acType;
    private String subtype;
    private String customizedType;
    private String nameOfAircraft;
    private Long numberOfSeats;
    private String registrar;
    private String owner;
    private String contract;
}
