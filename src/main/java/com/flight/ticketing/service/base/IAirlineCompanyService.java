package com.flight.ticketing.service.base;

import com.flight.ticketing.model.dto.AirlineCompany;
import com.flight.ticketing.model.response.GenericResponse;

import java.util.List;

public interface IAirlineCompanyService {
    AirlineCompany createAirlineCompany(AirlineCompany airlineCompany);

    List<AirlineCompany> searchAirlineCompanyList(String airlineCompanyName);

    AirlineCompany inquireAirlineCompany(Long airlineCompanyId);
}
