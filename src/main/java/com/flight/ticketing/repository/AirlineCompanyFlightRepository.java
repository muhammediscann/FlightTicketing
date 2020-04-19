package com.flight.ticketing.repository;

import com.flight.ticketing.model.entity.AirlineCompanyFlightEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirlineCompanyFlightRepository extends JpaRepository<AirlineCompanyFlightEntity, Long> {
}
