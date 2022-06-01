package com.practicalddd.cargotracker.booking.domain.model.aggregate;

import lombok.Getter;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
@Getter
public class RouteSpecification {
    @ManyToOne
    private Location origin;
}
