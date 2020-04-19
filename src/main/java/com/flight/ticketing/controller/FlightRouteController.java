package com.flight.ticketing.controller;

import com.flight.ticketing.model.dto.FlightRoute;
import com.flight.ticketing.service.base.IFlightRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/flight-route")
public class FlightRouteController {

    @Autowired
    private IFlightRouteService flightRouteService;

    @PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FlightRoute> createFlightRoute(@RequestBody FlightRoute flightRoute){
        return ResponseEntity.ok(flightRouteService.createFlightRoute(flightRoute));
    }
}
