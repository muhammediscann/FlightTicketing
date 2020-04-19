package com.flight.ticketing.repository;

import com.flight.ticketing.model.entity.FlightRouteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRouteRepository extends JpaRepository<FlightRouteEntity, Long> {
}
