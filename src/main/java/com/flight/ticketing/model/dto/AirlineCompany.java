package com.flight.ticketing.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AirlineCompany {
    private Long airlineCompanyId;
    private String airlineCompanyName;
}
