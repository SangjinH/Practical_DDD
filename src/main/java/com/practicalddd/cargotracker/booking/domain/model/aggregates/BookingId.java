package com.practicalddd.cargotracker.booking.domain.model.aggregates;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class BookingId implements Serializable {

    @Column(name = "booking_id", unique = true, updatable = false)
    private String id;
}
