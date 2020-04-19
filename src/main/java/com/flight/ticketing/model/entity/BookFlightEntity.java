package com.flight.ticketing.model.entity;

import com.flight.ticketing.model.entity.base.BaseEntity;
import com.flight.ticketing.model.enumeration.BookFlightCode;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "book_flight")
public class BookFlightEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookFlightId;

    @OneToOne
    @JoinColumn(name = "airline_company_flight_id")
    private AirlineCompanyFlightEntity airlineCompanyFlight;

    @Enumerated(EnumType.STRING)
    private BookFlightCode bookFlightCode;

    private BigDecimal price;
    private String pnrCode;

}
