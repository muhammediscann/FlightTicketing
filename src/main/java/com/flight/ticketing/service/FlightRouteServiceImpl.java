package com.flight.ticketing.service;

import com.flight.ticketing.model.dto.FlightRoute;
import com.flight.ticketing.model.entity.FlightRouteEntity;
import com.flight.ticketing.repository.FlightRouteRepository;
import com.flight.ticketing.service.base.IAirportService;
import com.flight.ticketing.service.base.IFlightRouteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlightRouteServiceImpl implements IFlightRouteService {

    @Autowired
    private FlightRouteRepository flightRouteRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private IAirportService airportService;

    @Override
    public FlightRoute createFlightRoute(FlightRoute flightRoute) {
        validateRequestParameter(flightRoute);
        FlightRouteEntity flightRouteEntity = modelMapper.map(flightRoute, FlightRouteEntity.class);
        flightRouteEntity = flightRouteRepository.save(flightRouteEntity);
        return modelMapper.map(flightRouteEntity, FlightRoute.class);
    }

    private void validateRequestParameter(FlightRoute flightRoute) {
        airportService.inquireAirlineCompany(flightRoute.getDepartureAirport().getAirportId());
        airportService.inquireAirlineCompany(flightRoute.getArrivalAirport().getAirportId());
    }
}
