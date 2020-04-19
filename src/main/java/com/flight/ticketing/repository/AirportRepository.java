package com.flight.ticketing.repository;

import com.flight.ticketing.model.entity.AirportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AirportRepository extends JpaRepository<AirportEntity, Long> {
    List<AirportEntity> findAllByAirportNameStartingWithIgnoreCase(String airlineCompanyName);
}
