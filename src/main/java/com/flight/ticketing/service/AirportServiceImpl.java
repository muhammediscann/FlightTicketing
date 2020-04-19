package com.flight.ticketing.service;

import com.flight.ticketing.exception.OperationResultException;
import com.flight.ticketing.model.dto.Airport;
import com.flight.ticketing.model.entity.AirportEntity;
import com.flight.ticketing.model.response.OperationResult;
import com.flight.ticketing.repository.AirportRepository;
import com.flight.ticketing.service.base.IAirportService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class AirportServiceImpl implements IAirportService {

    @Autowired
    private AirportRepository airportRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Airport createAirport(Airport airport) {
        AirportEntity airportEntity = modelMapper.map(airport, AirportEntity.class);
        airportEntity = airportRepository.save(airportEntity);
        return modelMapper.map(airportEntity, Airport.class);
    }

    @Override
    public List<Airport> searchAirlineCompanyList(String airportName){
        validateSearchInput(airportName);
        List<Airport> airportList = new ArrayList<>();
        List<AirportEntity> airportEntityList = airportRepository
                .findAllByAirportNameStartingWithIgnoreCase(airportName);

        if (airportEntityList != null) {
            airportList = modelMapper.map(airportEntityList, new TypeToken<List<Airport>>() {}.getType());
        }

        return airportList;
    }

    @Override
    public Airport inquireAirlineCompany(Long airportId) {
        return airportRepository.findById(airportId)
                .map(airportEntity -> modelMapper.map(airportEntity, Airport.class))
                .orElseThrow(() -> OperationResultException
                        .builder()
                        .operationResult(OperationResult.createErrorResult("Airport Not Found!"))
                        .build());
    }

    private void validateSearchInput(String airportName) {
        if (StringUtils.isEmpty(airportName)) {
            throw new OperationResultException(OperationResult.createErrorResult("Please enter the value!"));
        }
    }
}
