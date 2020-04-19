package com.flight.ticketing.model.entity;

import com.flight.ticketing.model.entity.base.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "airline_company")
public class AirlineCompanyEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long airlineCompanyId;
    private String airlineCompanyName;
}
