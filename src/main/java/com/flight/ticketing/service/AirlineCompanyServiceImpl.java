package com.flight.ticketing.service;

import com.flight.ticketing.exception.OperationResultException;
import com.flight.ticketing.model.dto.AirlineCompany;
import com.flight.ticketing.model.entity.AirlineCompanyEntity;
import com.flight.ticketing.model.response.OperationResult;
import com.flight.ticketing.repository.AirlineCompanyRepository;
import com.flight.ticketing.service.base.IAirlineCompanyService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class AirlineCompanyServiceImpl implements IAirlineCompanyService {

    @Autowired
    private AirlineCompanyRepository airlineCompanyRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public AirlineCompany createAirlineCompany(AirlineCompany airlineCompany) {
        AirlineCompanyEntity airlineCompanyEntity = modelMapper.map(airlineCompany, AirlineCompanyEntity.class);
        airlineCompanyEntity = airlineCompanyRepository.save(airlineCompanyEntity);
        return modelMapper.map(airlineCompanyEntity, AirlineCompany.class);
    }

    public List<AirlineCompany> searchAirlineCompanyList(String airlineCompanyName){
        validateSearchInput(airlineCompanyName);
        List<AirlineCompany> airlineCompanyList = new ArrayList<>();
        List<AirlineCompanyEntity> airlineCompanyEntityList = airlineCompanyRepository
                .findAllByAirlineCompanyNameStartingWithIgnoreCase(airlineCompanyName);

        if (airlineCompanyEntityList != null) {
            airlineCompanyList = modelMapper.map(airlineCompanyEntityList, new TypeToken<List<AirlineCompany>>() {}.getType());
        }

        return airlineCompanyList;
    }

    @Override
    public AirlineCompany inquireAirlineCompany(Long airlineCompanyId) {
        return airlineCompanyRepository.findById(airlineCompanyId)
                .map(airlineCompanyEntity -> modelMapper.map(airlineCompanyEntity, AirlineCompany.class))
                .orElseThrow(() -> OperationResultException
                        .builder()
                        .operationResult(OperationResult.createErrorResult("Airline Company Not Found!"))
                        .build());
    }

    private void validateSearchInput(String airlineCompanyName) {
        if (StringUtils.isEmpty(airlineCompanyName)) {
            throw new OperationResultException(OperationResult.createErrorResult("Please enter the value!"));
        }
    }
}
