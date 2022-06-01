package com.practicalddd.cargotracker.booking.domain.model.aggregate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Location {

    @Id
    @GeneratedValue
    private Long id;
}
