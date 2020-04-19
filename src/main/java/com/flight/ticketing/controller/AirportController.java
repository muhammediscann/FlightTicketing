package com.flight.ticketing.controller;

import com.flight.ticketing.model.dto.Airport;
import com.flight.ticketing.service.base.IAirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/airport")
public class AirportController {

    @Autowired
    private IAirportService airportService;

    @PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Airport> createAirport(@RequestBody Airport airport){
        return ResponseEntity.ok(airportService.createAirport(airport));
    }

    @GetMapping(path = "/search")
    public ResponseEntity<List<Airport>> searchAirportList(@RequestParam String airportName){
        return ResponseEntity.ok(airportService.searchAirlineCompanyList(airportName));
    }
}
