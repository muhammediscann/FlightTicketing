package com.flight.ticketing.repository;

import com.flight.ticketing.model.entity.AirlineCompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AirlineCompanyRepository extends JpaRepository<AirlineCompanyEntity, Long> {
    List<AirlineCompanyEntity> findAllByAirlineCompanyNameStartingWithIgnoreCase(String airlineCompanyName);
}
