package com.flight.ticketing.model.entity;


import com.flight.ticketing.model.entity.base.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
@Table(name = "flight_route")
@Entity
public class FlightRouteEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long flightRouteId;

    @OneToOne
    @JoinColumn(name = "departure_airport_id")
    private AirportEntity departureAirport;

    @OneToOne
    @JoinColumn(name = "arrival_airport_id")
    private AirportEntity arrivalAirport;

    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
}
