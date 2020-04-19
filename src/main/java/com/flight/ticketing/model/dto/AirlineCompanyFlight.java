package com.flight.ticketing.model.dto;

import com.flight.ticketing.model.entity.AirlineCompanyEntity;
import com.flight.ticketing.model.entity.FlightRouteEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AirlineCompanyFlight {
    private Long airlineCompanyFlightId;
    private AirlineCompany airlineCompany;
    private FlightRoute flightRoute;
    private Integer totalTicketCount;
    private Integer remainingTicketCount;
    private BigDecimal initialPrice;
    private BigDecimal price;
}
