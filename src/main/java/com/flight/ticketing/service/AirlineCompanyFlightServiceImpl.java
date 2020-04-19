package com.flight.ticketing.service;

import com.flight.ticketing.exception.OperationResultException;
import com.flight.ticketing.model.dto.AirlineCompanyFlight;
import com.flight.ticketing.model.entity.AirlineCompanyFlightEntity;
import com.flight.ticketing.model.enumeration.BookFlightCode;
import com.flight.ticketing.model.response.OperationResult;
import com.flight.ticketing.repository.AirlineCompanyFlightRepository;
import com.flight.ticketing.service.base.IAirlineCompanyFlightService;
import com.flight.ticketing.utility.Constants;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AirlineCompanyFlightServiceImpl implements IAirlineCompanyFlightService {

    @Autowired
    private AirlineCompanyFlightRepository airlineCompanyFlightRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public AirlineCompanyFlight createAirlineCompanyFlight(AirlineCompanyFlight airlineCompanyFlight) {
        AirlineCompanyFlightEntity airlineCompanyFlightEntity = modelMapper.map(airlineCompanyFlight, AirlineCompanyFlightEntity.class);
        airlineCompanyFlightEntity = airlineCompanyFlightRepository.save(airlineCompanyFlightEntity);
        return modelMapper.map(airlineCompanyFlightEntity, AirlineCompanyFlight.class);
    }

    @Override
    public AirlineCompanyFlightEntity inquireAirlineCompanyFlight(Long airlineCompanyFlightId) {
        return airlineCompanyFlightRepository
                .findById(airlineCompanyFlightId)
                .orElseThrow(() -> OperationResultException.builder().operationResult(
                        OperationResult.createErrorResult("Flight Not Found!")).build());
    }

    public AirlineCompanyFlightEntity inquireAndValidateAirlineCompanyFlight(Long airlineCompanyFlightId) {
        AirlineCompanyFlightEntity airlineCompanyFlightEntity = inquireAirlineCompanyFlight(airlineCompanyFlightId);
        if (airlineCompanyFlightEntity.getRemainingTicketCount() == Constants.NO_ELEMENT_NUMBER) {
            throw OperationResultException.builder().operationResult(OperationResult.createErrorResult("Sorry! The ticket is over!")).build();
        }

        return airlineCompanyFlightEntity;
    }

    @Override
    public OperationResult updateAirlineCompanyFlight(AirlineCompanyFlightEntity airlineCompanyFlightEntity, BookFlightCode code) {
        if (BookFlightCode.BOOKED.equals(code)) {
            airlineCompanyFlightEntity.setRemainingTicketCount(airlineCompanyFlightEntity.getRemainingTicketCount() - Constants.DEFAULT_ELEMENT_NUMBER);
        } else {
            airlineCompanyFlightEntity.setRemainingTicketCount(airlineCompanyFlightEntity.getRemainingTicketCount() + Constants.DEFAULT_ELEMENT_NUMBER);
        }

        final double newPercentage = calculatePercentage(airlineCompanyFlightEntity.getRemainingTicketCount(), airlineCompanyFlightEntity.getTotalTicketCount());
        airlineCompanyFlightEntity.setPrice(airlineCompanyFlightEntity.getInitialPrice());

        if (newPercentage >= Constants.PERCENTAGE) {
            final int priceSegment = (int) (newPercentage / Constants.PERCENTAGE);
            airlineCompanyFlightEntity
                    .setPrice(calculateNewPrice(airlineCompanyFlightEntity, priceSegment));
        }
        return OperationResult.createSuccessResult("Updated");
    }

    private BigDecimal calculateNewPrice(AirlineCompanyFlightEntity airlineCompanyFlightEntity, int priceSegment) {
        return airlineCompanyFlightEntity.getInitialPrice()
                .add(airlineCompanyFlightEntity.getInitialPrice()
                        .multiply(BigDecimal.valueOf(priceSegment))
                        .divide(BigDecimal.valueOf(100 / Constants.PERCENTAGE), 2, BigDecimal.ROUND_DOWN));
    }

    private double calculatePercentage(Integer obtained, Integer total) {
        return (total - obtained) * 100 / total.doubleValue();
    }
}
