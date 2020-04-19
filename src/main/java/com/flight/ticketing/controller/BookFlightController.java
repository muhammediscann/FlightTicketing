package com.flight.ticketing.controller;

import com.flight.ticketing.model.dto.BookFlight;
import com.flight.ticketing.model.request.BookFlightRequest;
import com.flight.ticketing.model.response.BookFlightKey;
import com.flight.ticketing.model.response.OperationResult;
import com.flight.ticketing.service.base.IBookFlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/book")
public class BookFlightController {

    @Autowired
    private IBookFlightService bookFlightService;

    @PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookFlightKey> createBookFlight(@RequestBody BookFlightRequest bookFlightRequest){
        return ResponseEntity.ok(bookFlightService.createBookFlight(bookFlightRequest));
    }

    @PostMapping(path = "/cancel", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OperationResult> cancelBookFlight(@RequestBody BookFlightKey bookFlightKey){
        return ResponseEntity.ok(bookFlightService.cancelBookFlight(bookFlightKey));
    }

    @PostMapping(path = "/search", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookFlight> searchBookFlight(@RequestBody BookFlightKey bookFlightKey){
        return ResponseEntity.ok(bookFlightService.searchBookFlight(bookFlightKey));
    }
}
