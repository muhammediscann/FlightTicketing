package com.flight.ticketing.service.base;

import com.flight.ticketing.model.dto.BookFlight;
import com.flight.ticketing.model.request.BookFlightRequest;
import com.flight.ticketing.model.response.BookFlightKey;
import com.flight.ticketing.model.response.OperationResult;

public interface IBookFlightService {
    BookFlightKey createBookFlight(BookFlightRequest request);

    OperationResult cancelBookFlight(BookFlightKey bookFlightKey);

    BookFlight searchBookFlight(BookFlightKey bookFlightKey);
}
