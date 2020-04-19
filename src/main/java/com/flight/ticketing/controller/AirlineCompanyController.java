package com.flight.ticketing.controller;

import com.flight.ticketing.model.dto.AirlineCompany;
import com.flight.ticketing.service.base.IAirlineCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/airline-company")
public class AirlineCompanyController {

    @Autowired
    private IAirlineCompanyService airlineCompanyService;

    @PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AirlineCompany> createAirlineCompany(@RequestBody AirlineCompany airlineCompany){
        return ResponseEntity.ok(airlineCompanyService.createAirlineCompany(airlineCompany));
    }

    @GetMapping(path = "/search")
    public ResponseEntity<List<AirlineCompany>> searchAllAirlineCompany(@RequestParam String airlineCompanyName){
        return ResponseEntity.ok(airlineCompanyService.searchAirlineCompanyList(airlineCompanyName));
    }
}
