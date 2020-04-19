package com.flight.ticketing.repository;

import com.flight.ticketing.model.dto.BookFlight;
import com.flight.ticketing.model.entity.BookFlightEntity;
import com.flight.ticketing.model.enumeration.BookFlightCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookFlightRepository extends JpaRepository<BookFlightEntity, Long> {
    Optional<BookFlightEntity> findByPnrCodeAndBookFlightCode(String pnrCode, BookFlightCode bookFlightCode);
}
