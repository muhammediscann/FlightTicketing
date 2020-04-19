package com.flight.ticketing.controller;

import com.flight.ticketing.model.dto.AirlineCompanyFlight;
import com.flight.ticketing.service.base.IAirlineCompanyFlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/airline-flight")
public class AirlineCompanyFlightController {

    @Autowired
    private IAirlineCompanyFlightService airlineCompanyFlightService;

    @PostMapping(path = "/create", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AirlineCompanyFlight> createAirlineCompanyFlight(@RequestBody AirlineCompanyFlight airlineCompanyFlight){
        return ResponseEntity.ok(airlineCompanyFlightService.createAirlineCompanyFlight(airlineCompanyFlight));
    }
}
