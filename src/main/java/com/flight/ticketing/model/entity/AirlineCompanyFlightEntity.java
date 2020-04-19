package com.flight.ticketing.model.entity;

import com.flight.ticketing.model.entity.base.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "flight_airline_company")
public class AirlineCompanyFlightEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long airlineCompanyFlightId;

    @OneToOne
    @JoinColumn(name = "airline_company_id")
    private AirlineCompanyEntity airlineCompany;

    @OneToOne
    @JoinColumn(name = "flight_route_id")
    private FlightRouteEntity flightRoute;

    private Integer totalTicketCount;
    private Integer remainingTicketCount;
    private BigDecimal initialPrice;
    private BigDecimal price;
}
