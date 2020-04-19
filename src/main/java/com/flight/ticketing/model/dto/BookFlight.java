package com.flight.ticketing.model.dto;

import com.flight.ticketing.model.entity.AirlineCompanyFlightEntity;
import com.flight.ticketing.model.enumeration.BookFlightCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookFlight {
    private Long bookFlightId;
    private AirlineCompanyFlight airlineCompanyFlight;
    private BookFlightCode bookFlightCode;
    private BigDecimal price;
    private String pnrCode;
}
