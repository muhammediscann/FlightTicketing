package com.flight.ticketing.service.base;

import com.flight.ticketing.model.dto.AirlineCompanyFlight;
import com.flight.ticketing.model.entity.AirlineCompanyFlightEntity;
import com.flight.ticketing.model.enumeration.BookFlightCode;
import com.flight.ticketing.model.response.OperationResult;

public interface IAirlineCompanyFlightService {

    AirlineCompanyFlight createAirlineCompanyFlight(AirlineCompanyFlight airlineCompanyFlight);

    AirlineCompanyFlightEntity inquireAirlineCompanyFlight(Long airlineCompanyFlightId);

    AirlineCompanyFlightEntity inquireAndValidateAirlineCompanyFlight(Long airlineCompanyFlightId);

    OperationResult updateAirlineCompanyFlight(AirlineCompanyFlightEntity airlineCompanyFlightEntity, BookFlightCode code);


}
