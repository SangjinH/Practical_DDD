package com.practicalddd.cargotracker.booking.domain.model.valueobjects;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter @Setter
@NoArgsConstructor
public class BookingAmount {

    @Column(name = "booking_amount", unique = true, updatable= false)
    private Integer bookingAmount;

    public BookingAmount(Integer bookingAmount){this.bookingAmount = bookingAmount;}
}