package com.flight.ticketing.service.base;

import com.flight.ticketing.model.dto.Airport;

import java.util.List;

public interface IAirportService {
    Airport createAirport(Airport airport);

    List<Airport> searchAirlineCompanyList(String airportName);

    Airport inquireAirlineCompany(Long airportId);
}
