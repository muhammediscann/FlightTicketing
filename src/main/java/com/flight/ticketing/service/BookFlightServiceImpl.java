package com.flight.ticketing.service;

import com.flight.ticketing.exception.OperationResultException;
import com.flight.ticketing.model.dto.BookFlight;
import com.flight.ticketing.model.entity.AirlineCompanyFlightEntity;
import com.flight.ticketing.model.entity.BookFlightEntity;
import com.flight.ticketing.model.enumeration.BookFlightCode;
import com.flight.ticketing.model.request.BookFlightRequest;
import com.flight.ticketing.model.response.BookFlightKey;
import com.flight.ticketing.model.response.OperationResult;
import com.flight.ticketing.repository.BookFlightRepository;
import com.flight.ticketing.service.base.IAirlineCompanyFlightService;
import com.flight.ticketing.service.base.IBookFlightService;
import com.flight.ticketing.utility.CommonUtility;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.nio.charset.Charset;
import java.util.Random;

@Service
public class BookFlightServiceImpl implements IBookFlightService {

    @Autowired
    private IAirlineCompanyFlightService airlineCompanyFlightService;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private BookFlightRepository bookFlightRepository;

    @Transactional
    @Override
    public BookFlightKey createBookFlight(BookFlightRequest request) {
        AirlineCompanyFlightEntity airlineCompanyFlightEntity = airlineCompanyFlightService
                .inquireAndValidateAirlineCompanyFlight(request.getAirlineCompanyFlightId());

        BookFlightEntity bookFlightEntity = BookFlightEntity.builder()
                .airlineCompanyFlight(airlineCompanyFlightEntity)
                .bookFlightCode(BookFlightCode.BOOKED)
                .price(airlineCompanyFlightEntity.getPrice())
                .pnrCode(generatePnrCode())
                .build();
        bookFlightEntity = bookFlightRepository.save(bookFlightEntity);

        OperationResult operationResult = airlineCompanyFlightService
                .updateAirlineCompanyFlight(airlineCompanyFlightEntity, BookFlightCode.BOOKED);

        if (!CommonUtility.isOperationSuccessfully(operationResult)) {
            throw OperationResultException.builder().operationResult(operationResult).build();
        }

        return BookFlightKey.builder()
                .pnrCode(bookFlightEntity.getPnrCode())
                .build();
    }

    @Transactional
    @Override
    public OperationResult cancelBookFlight(BookFlightKey bookFlightKey) {
        BookFlightEntity bookFlightEntity = inquireBookFlight(bookFlightKey.getPnrCode());
        bookFlightEntity.setBookFlightCode(BookFlightCode.CANCEL);
        bookFlightEntity = bookFlightRepository.save(bookFlightEntity);

        AirlineCompanyFlightEntity airlineCompanyFlightEntity = airlineCompanyFlightService
                .inquireAndValidateAirlineCompanyFlight(bookFlightEntity.getAirlineCompanyFlight().getAirlineCompanyFlightId());
        OperationResult operationResult = airlineCompanyFlightService.updateAirlineCompanyFlight(airlineCompanyFlightEntity, bookFlightEntity.getBookFlightCode());

        if (!CommonUtility.isOperationSuccessfully(operationResult)) {
            throw OperationResultException.builder().operationResult(operationResult).build();
        }

        return operationResult;
    }

    @Override
    public BookFlight searchBookFlight(BookFlightKey bookFlightKey) {
        return bookFlightRepository.findByPnrCodeAndBookFlightCode(bookFlightKey.getPnrCode(), BookFlightCode.BOOKED)
                .map(bookFlightEntity -> modelMapper.map(bookFlightEntity, BookFlight.class))
                .orElseThrow(() -> OperationResultException.builder()
                        .operationResult(OperationResult.createErrorResult("Ticket Not Found!")).build());
    }

    private BookFlightEntity inquireBookFlight(String pnrCode) {
        return bookFlightRepository.findByPnrCodeAndBookFlightCode(pnrCode, BookFlightCode.BOOKED)
                .orElseThrow(() -> OperationResultException.builder()
                        .operationResult(OperationResult.createErrorResult("Ticket Not Found!")).build());
    }

    private String generatePnrCode() {
        byte[] array = new byte[256];
        new Random().nextBytes(array);

        String randomString = new String(array, Charset.forName("UTF-8"));

        String alphaNumericString
                = randomString
                .replaceAll("[^A-Z0-9]", "");

        StringBuilder stringBuilder = new StringBuilder();
        for (int k = 0; k < 8; k++) {
            if (Character.isLetter(alphaNumericString.charAt(k))
                    || Character.isDigit(alphaNumericString.charAt(k))) {
                stringBuilder.append(alphaNumericString.charAt(k));
            }
        }

        return stringBuilder.toString();
    }
}
